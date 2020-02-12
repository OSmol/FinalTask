package by.javatr.lemesheuski.library.dao;

import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.User;

public interface UserDAO {
    User findUserByLoginAndPassword(String login, String password) throws DAOException;
    boolean addUser(User user) throws DAOException;
}
