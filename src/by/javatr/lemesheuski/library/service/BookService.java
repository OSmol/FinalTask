package by.javatr.lemesheuski.library.service;

import by.javatr.lemesheuski.library.service.exception.ServiceException;

import java.util.List;

public interface BookService {
    String getAllBooks() throws ServiceException;
    boolean addBook(String title, String author, int year, List<String> genre, String annotation) throws ServiceException;
    boolean addBookToFavorite(String username, String title, String author) throws ServiceException;
    String getFavoriteBooks(String username) throws ServiceException;
    void deleteBook(String title, String author) throws ServiceException;
    void deleteBookFromFavorites(String username, String title, String author) throws ServiceException;
}
