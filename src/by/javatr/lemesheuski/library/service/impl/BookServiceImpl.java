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
                System.out.println(book.toString());
                str.append("\n"+book.getTitle()+"\n"+book.getAuthor()+"\n"+book.getYear()+"\n");
                String genres ="";
                for(String genre:book.getGenres())
                    genres+=genre+" ";
                str.append(genres.trim()+"\n    ");
                String annotation = book.getAnnotation();
                if(annotation.length()>149) {
                    for (int i = 0, j = 149; j <= annotation.length()+149; i += 149, j += 149) {
                        if(annotation.length()>j+1){
                            if (annotation.substring(j,j+1).matches("[.?!, \\-]")) {
                                str.append(annotation.substring(i, j + 1).trim() + "\n");
                                i++;
                                j++;
                            }
                            else
                                str.append(annotation.substring(i, j).trim() + "-\n");
                        }
                        else{
                            str.append(annotation.substring(i) + "\n\n\n");
                        }
                    }
                }else{
                    str.append(annotation+"\n\n\n");
                }
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
}
