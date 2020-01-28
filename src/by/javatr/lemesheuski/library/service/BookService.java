package by.javatr.lemesheuski.library.service;

import by.javatr.lemesheuski.library.entity.Book;
import by.javatr.lemesheuski.library.service.exception.BookServiceException;

import java.util.List;

public interface BookService {
    String getAllBooks()throws BookServiceException;
    boolean addBook(String title, String author, int year, List<String> genre, String annotation) throws BookServiceException;
}
