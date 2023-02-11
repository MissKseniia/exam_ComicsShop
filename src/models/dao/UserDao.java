package models.dao;

import models.ComicBook;
import models.User;

import java.util.Collection;
import java.util.List;

/**
 * CRUD - CREATE READ UPDATE DELETE
 */
public interface UserDao {
    List<User> allUsers();
    User findById(int id);

    boolean register(User user);
    boolean auth(User user);

    void booking(User user, Collection<? extends ComicBook> books);
    void buying(User user, Collection<? extends ComicBook> books);

    default void save() {
        throw new UnsupportedOperationException();
    }

}
