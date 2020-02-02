package by.javatr.lemesheuski.library.dao;

import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll() throws DAOException;
    User findUserByLogin(String login) throws DAOException;
    boolean addUser(String login, String password) throws DAOException;
}
