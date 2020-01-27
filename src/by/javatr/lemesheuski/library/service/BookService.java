package by.javatr.lemesheuski.library.service;

import by.javatr.lemesheuski.library.entity.Book;
import by.javatr.lemesheuski.library.service.exception.BookServiceException;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks()throws BookServiceException;
    boolean AddBook(String title, String author, int year, String annotation, List<String> genre) throws BookServiceException;
}
