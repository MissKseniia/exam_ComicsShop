package models;

import java.io.Serializable;
import java.util.Objects;

public class ComicBook implements Serializable {
    //private User user;

    private transient static int COUNT = 0;
    private final int id;

    private String title;
    private String author;
    private String publishingHouse;
    private String pages;
    private String genre;
    private String publicationYear;
    private float costPrice;
    private float sellingPrice;
    private boolean single;
    //Необходимо хранить следующую информацию о комиксах: название комикса,
    //ФИО художника/автора, название издательства комикса, количество страниц,
    //жанр, год издания, себестоимость, цена для продажи, является ли комикс
    // продолжением какого-то другого комикса или серии комиксов.
    public ComicBook(String title, String author, String publishingHouse,
                     String pages, String genre, String publicationYear,
                     float costPrice, float sellingPrice, boolean single){
        this.id = ++COUNT;
        this.title = title;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.publicationYear = publicationYear;
        this.pages = pages;
        this.genre = genre;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.single = single;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(float costPrice) {
        this.costPrice = costPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }


    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBook comicBook = (ComicBook) o;
        return id == comicBook.id
                && title.equals(comicBook.title)
                && author.equals(comicBook.author)
                && publishingHouse.equals(comicBook.publishingHouse)
                && publicationYear.equals(comicBook.publicationYear)
                && pages.equals(comicBook.pages)
                && genre.equals(comicBook.genre)
                && costPrice == comicBook.costPrice
                && sellingPrice == comicBook.sellingPrice
                && single == comicBook.single;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publishingHouse
                , publicationYear, pages, genre
                , costPrice, sellingPrice, single);
    }
    @Override
    public String toString() {
        return "Comic Book {" +
                "\nid=" + id +
                "\n1. title='" + title + '\'' +
                "\n2. author='" + author + '\'' +
                "\n3. publishing house=" + publishingHouse + '\'' +
                "\n4. publication year=" + publicationYear + '\'' +
                "\n5. pages=" + pages + '\'' +
                "\n6. genre=" + genre + '\'' +
                "\n7. cost price=" + costPrice + '\'' +
                "\n8. selling price=" + sellingPrice + '\'' +
                "\n9. is it a single=" + single + '\'' +

                '}';
    }
}
