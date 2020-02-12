package by.javatr.lemesheuski.library.dao;

import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getAll() throws DAOException;
    void addBook(Book book)throws DAOException;
    void deleteBook(String title, String author) throws DAOException;
    boolean addBookToFavorite(String username, String title, String author) throws DAOException;
    List<Book> getFavoriteBooks(String username) throws DAOException;
    void deleteBookFromFavorites(String username, String title, String author) throws DAOException;

}
