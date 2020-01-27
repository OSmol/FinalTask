package by.javatr.lemesheuski.library.service;

import by.javatr.lemesheuski.library.service.impl.BookServiceImpl;
import by.javatr.lemesheuski.library.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final UserService userService = new UserServiceImpl();
    private final BookService bookService = new BookServiceImpl();

    public UserService getUserService(){
        return userService;
    }
    public BookService getBookService() { return bookService; }
}
