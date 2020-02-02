package by.javatr.lemesheuski.library.dao;

import by.javatr.lemesheuski.library.dao.impl.BookDAOImpl;
import by.javatr.lemesheuski.library.dao.impl.UserDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private DAOFactory(){}

    public static DAOFactory getInstance() {
        return instance;
    }

    private final UserDAO userDAO = new UserDAOImpl();
    private final BookDAO bookDAO = new BookDAOImpl();

    public UserDAO getUserService(){
        return userDAO;
    }
    public BookDAO getBookService() { return bookDAO; }
}
