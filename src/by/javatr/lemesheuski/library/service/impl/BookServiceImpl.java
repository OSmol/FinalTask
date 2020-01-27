package by.javatr.lemesheuski.library.service.impl;

import by.javatr.lemesheuski.library.dao.BookDAO;
import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.Book;
import by.javatr.lemesheuski.library.service.BookService;
import by.javatr.lemesheuski.library.service.exception.BookServiceException;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public List<Book> getAllBooks() throws BookServiceException {
        try {
            return BookDAO.getAll();
        }catch (DAOException e){
            throw new BookServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean AddBook(String title, String author, int year, String annotation, List<String> genre) throws BookServiceException {
        if(title!=null||author!=null||annotation!=null||!genre.isEmpty()){
            try {
                BookDAO.addBook(title, author, year, annotation, genre);
            }catch (DAOException e){
                throw new BookServiceException(e.getMessage(), e);
            }
        }else{
            throw new BookServiceException("Some fields are empty");
        }
    }
}
