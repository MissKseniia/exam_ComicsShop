package models.dao;

import models.ComicBook;
import models.User;

import java.io.*;
import java.util.*;

/**
 * DAO, который связан с бинарным файлом
 */
public class UserDaoInFileImpl implements UserDao{
    private final String fileName;
    private List<User> users;


    public UserDaoInFileImpl(String fileName) {
        this.fileName = fileName;
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            users = (List<User>) inputStream.readObject();
        } catch(IOException | ClassNotFoundException ex) {
            users = new ArrayList<>();
        }
    }

    @Override
    public void save() {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(users);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public List<User> allUsers() {
        return users;
    }


    @Override
    public User findById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst().orElseThrow();
    }


    @Override
    public boolean register(User user) {
        Optional<User> existedUser = users.stream()
                .filter(u ->
                        u.getNickname().equals(user.getNickname())
                ).findFirst();
        if(existedUser.isPresent()) {
            return false;
        }
        users.add(user);
        return true;
    }

    @Override
    public boolean auth(User user) {
        Optional<User> existingUser = users.stream()
                .filter(u -> u.getNickname().equals(user.getNickname())
                        && u.getPassword().equals(user.getPassword())).findAny();

        if(existingUser.isPresent()) {
            user.setId(existingUser.get().getId());
            return true;
        }
        return false;
    }

    @Override
    public void booking(User user, Collection<? extends ComicBook> books) {
        user.getUserBooks_Booked().addAll(books);
    }

    @Override
    public void buying(User user, Collection<? extends ComicBook> books) {
        user.getUserBooks_Bought().addAll(books);
    }
}

