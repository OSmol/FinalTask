package by.javatr.lemesheuski.library.service;

import by.javatr.lemesheuski.library.entity.User;
import by.javatr.lemesheuski.library.service.exception.ServiceException;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;
    boolean register(String login,String password) throws ServiceException;



































}
