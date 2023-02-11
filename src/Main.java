import models.ComicBook;
import models.User;
import models.dao.ComicBookDaoInFileImpl;
import models.dao.UserDaoInFileImpl;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //Расписать алгоритм работы приложения по меню (свитч)
        Shop shop = new Shop();
        ComicBookDaoInFileImpl comicBookDaoInFile = new ComicBookDaoInFileImpl("comics");
        UserDaoInFileImpl userDaoInFile = new UserDaoInFileImpl("users");
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        User user;

        /* ***MENU***\n\n" +
         "1. Добавить комикс.\n" +
         "2. Удалить комикс.\n" +
         "3. Редактировать детали о комиксе.\n" +
         "4. Списать комикс.\n" +
         "5. Начать акцию.\n" +
         "6. Купить комикс.\n" +
         "7. Забронировать комикс.\n" +
         "8. Личный кабинет.\n" +
         "9. Выход." */




            while (!exit) {
                try {
                shop.showMenu();
                System.out.println("Выберите нужную цифру\n");
                switch (scanner.nextInt()) {

                    case 1:
    /* private String title;
    private String author;
    private String publishingHouse;
    private String pages;
    private String genre;
    private String publicationYear;
    private float costPrice;
    private float sellingPrice;
    private boolean single; */
                        scanner.nextLine();
                        System.out.println("\nВведите название комикса");
                        String title = scanner.nextLine();

                        System.out.println("\nВведите автора комикса");
                        String author = scanner.nextLine();

                        System.out.println("\nВведите название издательства");
                        String publishingHouse = scanner.nextLine();

                        System.out.println("\nВведите количество страниц");
                        String pages = scanner.nextLine();

                        System.out.println("\nВведите жанр комикса");
                        String genre = scanner.nextLine();

                        System.out.println("\nВведите год публикации");
                        String publicationYear = scanner.nextLine();

                        System.out.println("\nВведите себестоимость");
                        float costPrice = scanner.nextFloat();

                        System.out.println("\nВведите цену для продажи");
                        float sellingPrice = scanner.nextFloat();
                        scanner.nextLine();

                        System.out.println("\nЯвляется ли комикс продолжением серии?");
                        boolean single;

                        if (scanner.nextLine().equals("нет")) {
                            single = false;
                        } else {
                            single = true;
                        }
                        ComicBook comicBook = new ComicBook(title, author, publishingHouse,
                                pages, genre, publicationYear, costPrice, sellingPrice, single);
                        comicBookDaoInFile.allComicBooks().add(comicBook);
                        System.out.println(comicBook.toString());
                        break;

                    case 2:
                        shop.remove_comic(comicBookDaoInFile.allComicBooks().get(
                                        shop.usersChoice(comicBookDaoInFile.allComicBooks())),
                                comicBookDaoInFile.allComicBooks());
                        break;

                    case 3:
                        shop.edit_comicInf(comicBookDaoInFile.allComicBooks());
                        break;

                    case 4:
                        shop.decommission_comic(comicBookDaoInFile.allComicBooks());
                        break;

                    case 5:
                        MyTread thread = new MyTread();
                        thread.runPromotion(shop, comicBookDaoInFile);
                        shop.showMenu();
                        break;
                    case 6:
                        //Войти или зарегистрироваться
                        System.out.println("\nЧтобы приобрести комикс, сначала нужно войти\n");
                        userDaoInFile.buying(
                                enter(userDaoInFile),
                                shop.sell_comic(comicBookDaoInFile.allComicBooks())
                        );
                        break;

                    case 7:
                        System.out.println("\nЧтобы забронировать комикс, сначала нужно войти\n");

                        userDaoInFile.booking(
                                enter(userDaoInFile),
                                shop.booking_comic(comicBookDaoInFile.allComicBooks())
                        );
                        break;

                    case 8:
                        //Вход в личный кабинет
                        user = enter(userDaoInFile);
                        System.out.printf("\n***Личный кабинет %s***\n", user.getNickname());
                        System.out.println("\n1. Ваши бронирования\n" +
                                "\n2. Ваши покупки\n" +
                                "\n3. Вернуться в меню\n");
                        int answer;

                        while (true) {
                            answer = scanner.nextInt();
                            if (answer == 1) {
                                for (ComicBook x :
                                        user.getUserBooks_Booked()) {

                                    System.out.printf("\n - %s >> %s\n", x.getTitle(), x.getAuthor());
                                }
                            } else if (answer == 2) {
                                for (ComicBook x :
                                        user.getUserBooks_Bought()) {

                                    System.out.printf("\n - %s >> %s\n", x.getTitle(), x.getAuthor());
                                }
                            } else if (answer == 3) {
                                break;
                            } else {
                                System.out.println("\nТакого варианта нет\n");
                            }
                        }

                        break;

                    case 9:
                        System.out.println("\nВы закрыли приложение\n");
                        exit = true;
                        break;

                    default:
                        System.out.println("\nТакой цифры нет в списке\n");
                        break;

                }
            } catch (InputMismatchException e){
                System.out.println("Неверный формат ввода");
            }
        }


    }

    public static User enter(UserDaoInFileImpl userDaoInFile){
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;
        do {
            System.out.println("\nУ вас уже есть учетная запись? 1- Да, 2- Нет\n");
            switch (scanner.nextInt()){

                case 1:
                    // вход
                    scanner.nextLine();
                    System.out.println("\n***Вход***\n");
                    System.out.println("\nВведите свой никнейм\n");
                    String nickName = scanner.nextLine();

                    System.out.println("\nВведите свой пароль\n");
                    String password = scanner.nextLine();

                    for (User user:
                    userDaoInFile.allUsers()) {
                        if (user.getNickname().equals(nickName)&&
                                user.getPassword().equals(password)){
                            if (userDaoInFile.auth(user)){
                                System.out.println("\n***Вход был успешно выполнен***\n");
                                repeat = false;
                                return user;
                            }
                        }
                    }
                    if (repeat){
                        System.out.println("\nТакого пользователя не существует\n");
                    }
                    break;

                case 2:
                    //регистрация
                    scanner.nextLine();
                    System.out.println("\n***Регистрация***\n");

                    System.out.println("\nВведите новый никнейм\n");
                    String nickName_New = scanner.nextLine();

                    System.out.println("\nВведите новый пароль\n");
                    String password_New = scanner.nextLine();

                    User userNew = new User(nickName_New, password_New);

                    if (userDaoInFile.register(userNew)){
                        System.out.println("\n***Вы успешно зарегистрировались\n***");
                        repeat = false;
                        return userNew;
                    }
                    break;

                default:
                    System.out.println("\nТакого варианта ответа нет\n");
                    break;
            }
        }while(repeat);


        return null;
    }
    public static class MyTread extends Thread {

        public void runPromotion(Shop shop, ComicBookDaoInFileImpl comicBookDaoInFile) {
            shop.promotion(comicBookDaoInFile.allComicBooks());
            super.run();
        }
    }

}

