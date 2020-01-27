package by.javatr.lemesheuski.library.service.impl;

import by.javatr.lemesheuski.library.entity.Book;
import by.javatr.lemesheuski.library.service.BookService;
import by.javatr.lemesheuski.library.service.exception.BookServiceException;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public List<Book> getAllBooks() throws BookServiceException {
        return null;
    }

    @Override
    public List<Book> AddBook(String title, String author, int year, String annotation, List<String> genre) throws BookServiceException {
        return null;
    }
}
