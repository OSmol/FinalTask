package by.javatr.lemesheuski.library.service;

import by.javatr.lemesheuski.library.service.exception.UserServiceException;

public interface UserService {
    String signIn(String login, String password) throws UserServiceException;
    boolean register(String login,String password) throws UserServiceException;



































}
