package models.dao;

import java.io.*;
import java.util.*;

import models.ComicBook;

public class ComicBookDaoInFileImpl implements ComicBookDao{

    private String fileName;

    private List<ComicBook> allComicBooks;

    public ComicBookDaoInFileImpl(String fileName) {
        this.fileName = fileName;
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            allComicBooks = (List<ComicBook>) inputStream.readObject();
        } catch(IOException | ClassNotFoundException ex) {
            allComicBooks = new ArrayList<>();
        }
    }


    @Override
    public void save() {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(allComicBooks);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ComicBook> allComicBooks() {
        return allComicBooks;
    }

    @Override
    public ComicBook findById(int id) {
        return allComicBooks.stream()
                .filter(u -> u.getId() == id)
                .findFirst().orElseThrow();
    }

    @Override
    public ComicBook findByTitle(String title) {
        return allComicBooks.stream()
                .filter(u -> u.getTitle() == title)
                .findFirst().orElseThrow();
    }


}

