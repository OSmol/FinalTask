package by.javatr.lemesheuski.library.service;

import by.javatr.lemesheuski.library.service.exception.BookServiceException;

import java.util.List;

public interface BookService {
    String getAllBooks() throws BookServiceException;
    void addBook(String title, String author, int year, List<String> genre, String annotation) throws BookServiceException;
    void addBookToFavorite(String username, String title, String author) throws BookServiceException;
    String getFavoriteBooks(String username) throws BookServiceException;
    void deleteBook(String title, String author) throws BookServiceException;
    void deleteBookFromFavorites(String username, String title, String author) throws BookServiceException;
}
