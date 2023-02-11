import models.ComicBook;

import java.io.IOException;
import java.util.*;

public class Shop {


    private List<ComicBook> bought;
    private List<ComicBook> booked;
    private List<ComicBook> decommissioned;

    public Shop() {
        this.bought = new ArrayList<>();
        this.booked = new ArrayList<>();
        this.decommissioned = new ArrayList<>();
    }

    public void add_comic(ComicBook comicBook, List<ComicBook> bookList) {
        bookList.add(comicBook);
    }

    public void remove_comic(ComicBook comicBook, List<ComicBook> bookList) {
        bookList.remove(comicBook);
        System.out.println("Комикс удален");

    }


    public void edit_comicInf(List<ComicBook> bookList) {
        //Меню (выбрать из списка комикс -- выбрать категорию( можно сразу несколько ) -- изменить)
        Map<String, Integer> categories = new HashMap<>();
        categories.put("Title", 1);
        categories.put("Author", 2);
        categories.put("PublishingHouse", 3);
        categories.put("PublicationYear", 4);
        categories.put("Pages", 5);
        categories.put("Genre", 6);
        categories.put("CostPrice", 7);
        categories.put("SellingPrice", 8);
        categories.put("IsSingle", 9);


        int index = usersChoice(bookList);
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        int answer2;
        do {
            System.out.println("\nВыберите категорию для изменения (цифра)\n");
            System.out.println(showCategories(bookList.get(index)));
            answer2 = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nИзмените данные: \n");
            switch (answer2){
                case 1:
                    bookList.get(index).setTitle(scanner.nextLine());
                    break;
                case 2:
                    bookList.get(index).setAuthor(scanner.nextLine());
                    break;
                case 3:
                    bookList.get(index).setPublishingHouse(scanner.nextLine());
                    break;
                case 4:
                    bookList.get(index).setPublicationYear(scanner.nextLine());
                    break;
                case 5:
                    bookList.get(index).setPages(scanner.nextLine());
                    break;
                case 6:
                    bookList.get(index).setGenre(scanner.nextLine());
                    break;
                case 7:
                    bookList.get(index).setCostPrice(scanner.nextFloat());
                    break;
                case 8:
                    bookList.get(index).setSellingPrice(scanner.nextFloat());
                    break;
                case 9:
                    bookList.get(index).setSingle(scanner.nextBoolean());
                    break;
                default:
                    System.out.println("\nОшибка\n");
                    break;
            }
            System.out.println("\nИзменения внесены\nЖелаете еще что-то отредактировать?\n1 - Да, 2 - Нет\n");
            if (scanner.nextInt()==2){stop=true;}
        } while (!stop);


    }

    private String showCategories(ComicBook comicBook) {
        return comicBook.toString();
    }

    public int usersChoice(List<ComicBook> bookList) {
        Scanner scanner = new Scanner(System.in);
        int index;
        System.out.println("\nВыберите комикс из списка (цифра)\n");
        int i = 1;
        for (ComicBook x:
                bookList) {
            System.out.printf("%d. %s\n", i, x.getTitle());
            i++;
        }
        index = scanner.nextInt()-1;
        return index;
    }



    public List<ComicBook> decommission_comic(List<ComicBook> bookList) throws IOException {
        int index = usersChoice(bookList);
        decommissioned.add(bookList.get(index));
        remove_comic(bookList.get(index), bookList);
        System.out.println("\nКомикс списан\n");
        return decommissioned;
    }

    public void promotion(List<ComicBook> bookList) {
        // Выбираем категорию (жанр или автор, устанавливаем скидку, выбираем сроки проведения акции
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nУкажите размер скидки\n");
        float discountSize = scanner.nextInt()/100;

        System.out.println("\nУстановите дату начала акции и ее завершения\n");
        System.out.println("Год");
        int year = scanner.nextInt();
        System.out.println("\nМесяц");
        int month = scanner.nextInt()-1;
        System.out.println("\nНачало (день)");
        int dayBegin = scanner.nextInt();
        System.out.println("\nКонец");
        int dayFinish = scanner.nextInt();

        Calendar startProm = new GregorianCalendar(year, month, dayBegin);
        Calendar finishProm = new GregorianCalendar(year, month, dayFinish);

        Date date1 = startProm.getTime();
        Date date2 = finishProm.getTime();
        System.out.println("Акция будет проводиться\n с " + date1 + "\nпо " + date2);

        int num = 1;
        String promotionGenre = null;
        String promotionAuthor = null;
        Map<String,Float> previousPrice = new HashMap<>();
        System.out.println("\nСкидка по жанру(1) или автору(2)?\n");

        boolean stop = false;
        do {
            switch (scanner.nextInt()){
                case 1:
                    scanner.nextLine();
                    System.out.println("\nВыберите жанр (цифра)\n");
                    for (ComicBook x:
                            bookList) {
                        System.out.printf("%d. %s\n", num, x.getGenre());
                        num++;
                    }
                    promotionGenre = bookList.get(scanner.nextInt()-1).getGenre();


                    stop = true;
                    break;
                case 2:
                    System.out.println("\nВыберите автора (цифра)\n");
                    for (ComicBook x:
                            bookList) {
                        System.out.printf("%d. %s\n", num, x.getAuthor());
                        num++;
                    }
                    promotionAuthor = bookList.get(scanner.nextInt()-1).getAuthor();

                    stop = true;
                    break;
                default:
                    System.out.println("\nНеподходящий ответ, попробуйте еще раз\n");
                    break;
            }
        }while (!stop);

        boolean repeat = true;
        Date currentDate = new Date ();
        System.out.println(currentDate);
        do {
            if (currentDate.equals(date1) && promotionGenre!=null){
                for (ComicBook x:
                        bookList) {
                    if (x.getGenre().equals(promotionGenre)){
                        previousPrice.put(x.getTitle(), x.getSellingPrice());
                        x.setSellingPrice(x.getSellingPrice()- x.getSellingPrice()*discountSize);
                        System.out.println("\n***Акция стартовала. Скидка установлена***\n");
                        repeat = false;
                    }
                }
            }else if (currentDate.equals(date1) && promotionAuthor!=null){
                for (ComicBook x:
                        bookList) {
                    if (x.getAuthor().equals(promotionAuthor)){
                        previousPrice.put(x.getTitle(), x.getSellingPrice());
                        x.setSellingPrice(x.getSellingPrice()- x.getSellingPrice()*discountSize);
                        System.out.println("\n***Акция стартовала. Скидка установлена***\n");
                        repeat = false;
                    }
                }
            }else {
                System.out.println("\nЕще рано до старта акции\n");
            }
        } while (repeat);

        boolean doNext = true;
        do {
            if (currentDate.equals(date2)){
                for (ComicBook x:
                        bookList) {
                    if (previousPrice.containsKey(x.getTitle())){
                        x.setSellingPrice(previousPrice.get(x.getTitle()));
                        previousPrice.remove(x.getTitle());
                        System.out.println("\n***Акция закончилась. Прежняя цена вернулась***\n");
                        doNext = false;
                    }
                }
            }
        }while (doNext);
    }


    public void showMenu() {
        System.out.println("\n***MENU***\n\n" +
                "1. Добавить комикс.\n" +
                "2. Удалить комикс.\n" +
                "3. Редактировать детали о комиксе.\n" +
                "4. Списать комикс.\n" +
                "5. Начать акцию.\n" +
                "6. Купить комикс.\n" +
                "7. Забронировать комикс.\n" +
                "8. Личный кабинет.\n" +
                "9. Выход.");
    }


    public List<ComicBook> booking_comic(List<ComicBook> bookList) {
        //выбор комикса - возможность положить в корзину несколько
        // появление забронированных комиксов в личном кабинете
        int index;
        Scanner scanner = new Scanner(System.in);
        boolean next = true;
        do {
            index = usersChoice(bookList);
            booked.add(bookList.get(index));
            remove_comic(bookList.get(index), bookList);
            System.out.println("\nЖелаете добавить еще один комикс в список?\n1 - Да, 2 - Нет\n");
            if (scanner.nextInt()==2) {
                next = false;
                System.out.println("\nБронирование прошло успешно!\n");

            }
        }while (next);

        return booked;
    }

    public List<ComicBook> sell_comic(List<ComicBook> bookList) throws IOException {
        int index;
        Scanner scanner = new Scanner(System.in);
        boolean next = true;
        do {
            index = usersChoice(bookList);
            bought.add(bookList.get(index));
            remove_comic(bookList.get(index), bookList);
            System.out.println("\nЖелаете добавить еще один комикс в список?\n1 - Да, 2 - Нет\n");
            if (scanner.nextInt()==2) {
                next = false;
                System.out.println("\nПоздравляем с покупкой!\n");
            }
        }while (next);

        return bought;
    }
}
