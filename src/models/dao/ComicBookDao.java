package models.dao;

import java.util.List;

import models.ComicBook;



public interface ComicBookDao {

    List<ComicBook> allComicBooks();
    ComicBook findById(int id);
    ComicBook findByTitle(String title);
    default void save() {
        throw new UnsupportedOperationException();
    }


}
