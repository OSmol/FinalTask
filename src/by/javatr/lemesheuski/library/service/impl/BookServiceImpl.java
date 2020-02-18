package by.javatr.lemesheuski.library.service.impl;

import by.javatr.lemesheuski.library.dao.BookDAO;
import by.javatr.lemesheuski.library.dao.DAOFactory;
import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.Book;
import by.javatr.lemesheuski.library.service.BookService;
import by.javatr.lemesheuski.library.service.exception.ServiceException;
import by.javatr.lemesheuski.library.service.impl.validator.BookValidator;
import by.javatr.lemesheuski.library.service.impl.validator.UserValidator;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookValidator bookValidator = new BookValidator();
    private UserValidator userValidator = new UserValidator();
    private BookDAO bookDAO = DAOFactory.getInstance().getBookService();

    @Override
    public String getAllBooks() throws ServiceException {// почему это метод возвращает String, а не List<Book>
        // откуда этот метод знает, как книги должны лыть отображены, может, их вообще никто никуда выводить не собирается
        try {
            List<Book> books = bookDAO.getAll();
            StringBuilder str = new StringBuilder();
            for (Book book : books) {
                str.append(book.toString());
            }
            return str.toString();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addBook(String title, String author, int year, List<String> genres, String annotation) throws ServiceException {
        if (bookValidator.isValidTitle(title) && bookValidator.isValidAuthor(author) && bookValidator.isValidYear(year) &&
                bookValidator.isValidGenre(genres) && bookValidator.isValidAnnotation(annotation)) {// покороче валидацию (ООП используй), я на четвертой проверке забыла, что делается
            Book book = new Book(title, author, year, annotation, genres);
            try {
                bookDAO.addBook(book);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addBookToFavorite(String username, String title, String author) throws ServiceException {
        if (userValidator.isLoginValid(username) && bookValidator.isValidTitle(title) && bookValidator.isValidAuthor(author)) {
            try {
                return bookDAO.addBookToFavorite(username, title, author);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            return false;
        }
    }

    @Override
    public String getFavoriteBooks(String username) throws ServiceException {
        if (userValidator.isLoginValid(username)) {
            try {
                List<Book> books = bookDAO.getFavoriteBooks(username);
                StringBuilder str = new StringBuilder();
                for (Book book : books) {
                    str.append(book.toString());
                }
                return str.toString();
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            return "There is no books";
        }
    }

    @Override
    public void deleteBook(String title, String author) throws ServiceException {
        if (bookValidator.isValidTitle(title) && bookValidator.isValidAuthor(author)) {
            try {
                bookDAO.deleteBook(title, author);
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage());
            }
        }
    }

    @Override
    public void deleteBookFromFavorites(String username, String title, String author) throws ServiceException {
        if (userValidator.isLoginValid(username) && bookValidator.isValidTitle(title) && bookValidator.isValidAuthor(author)) {
            try {
                bookDAO.deleteBookFromFavorites(username, title, author);
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage());
            }
        }
    }
}
