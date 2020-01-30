package by.javatr.lemesheuski.library.service.impl;

import by.javatr.lemesheuski.library.dao.BookDAO;
import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.Book;
import by.javatr.lemesheuski.library.service.BookService;
import by.javatr.lemesheuski.library.service.exception.BookServiceException;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public String getAllBooks() throws BookServiceException {
        try {
            List<Book> books = BookDAO.getAll();
            StringBuilder str = new StringBuilder();
            for(Book book:books){
                str.append(book.toString());
            }
            return str.toString();
        }catch (DAOException e){
            throw new BookServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void addBook(String title, String author, int year, List<String> genre, String annotation) throws BookServiceException {
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

    @Override
    public void addBookToFavorite(String username, String title, String author) throws BookServiceException {
        if(username!=null||title!=null||author!=null){
           try{
               BookDAO.addBookToFavorite(username, title, author);
           }catch (DAOException e){
               throw new BookServiceException(e.getMessage(), e);
           }
        }else{
            throw new BookServiceException("Some fields are empty");
        }
    }

    @Override
    public String getFavoriteBooks(String username) throws BookServiceException {
        if(username!=null){
            try{
                List<Book> books = BookDAO.getFavoriteBooks(username);
                StringBuilder str = new StringBuilder();
                for(Book book:books){
                    str.append(book.toString());
                }
                return str.toString();
            }catch (DAOException e){
                throw new BookServiceException(e.getMessage(), e);
            }
        }else{
            throw new BookServiceException("Some fields are empty");
        }
    }
}
