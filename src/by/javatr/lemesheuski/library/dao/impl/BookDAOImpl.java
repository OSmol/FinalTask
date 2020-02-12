package by.javatr.lemesheuski.library.dao.impl;

import by.javatr.lemesheuski.library.dao.BookDAO;
import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private String rootDir = "./resources";
    private String rootDirFavorites = "./resources/favorites";
    private String bookRepo = "books";
    private String fileExt = ".odt";

    @Override
    public List<Book> getAll() throws DAOException {
        return getAllFrom(getFile(rootDir, bookRepo + fileExt));
    }

    private List<Book> getAllFrom(File file) throws DAOException {
        List<Book> books = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object o;
            while (fis.available() > 0) {
                if ((o = ois.readObject()) != null) {
                    if (books.getClass() == o.getClass()) {
                        books = (List<Book>) o;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            return books;
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException(e);
        }
        return books;
    }

    public Book findBookByTitleAndAuthorFrom(String title, String author, File file) throws DAOException {
        List<Book> books = getAllFrom(file);
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author))
                return book;
        }
        return null;
    }

    @Override
    public boolean addBookToFavorite(String username, String title, String author) throws DAOException {
        File favoritesFile = getFile(rootDirFavorites, username + fileExt);
        File repoFile = getFile(rootDir, bookRepo + fileExt);
        Book book = findBookByTitleAndAuthorFrom(title, author, favoritesFile);
        List<Book> favoriteBooks = getAllFrom(favoritesFile);
        if (book == null) {
            book = findBookByTitleAndAuthorFrom(title, author, repoFile);
            if (book != null) {
                favoriteBooks.add(book);
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(favoritesFile))) {
                    oos.writeObject(favoriteBooks);
                } catch (IOException e) {
                    throw new DAOException(e);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Book> getFavoriteBooks(String username) throws DAOException {
        return getAllFrom(getFile(rootDirFavorites, username + fileExt));
    }

    @Override
    public void deleteBookFromFavorites(String username, String title, String author)
            throws DAOException {
        File favoritesFile = getFile(rootDirFavorites, username + fileExt);
        Book book = findBookByTitleAndAuthorFrom(title, author, favoritesFile);
        if (book != null) {
            List<Book> favoriteBooks = getAllFrom(favoritesFile);
            favoriteBooks.remove(book);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(favoritesFile))) {
                oos.writeObject(favoriteBooks);
            } catch (IOException e) {
                throw new DAOException(e);
            }
        }

    }

    @Override
    public void deleteBook(String title, String author)
            throws DAOException {
        File repoFile = getFile(rootDir, bookRepo + fileExt);
        List<Book> books = getAllFrom(repoFile);
        Book book = findBookByTitleAndAuthorFrom(title, author, repoFile);
        if (book != null) {
            books.remove(book);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(repoFile))) {
                oos.writeObject(books);
            } catch (IOException e) {
                throw new DAOException(e.getMessage());
            }
        }

    }

    @Override
    public void addBook(Book book) throws DAOException {
        File repoFile = getFile(rootDir, bookRepo + fileExt);
        if (findBookByTitleAndAuthorFrom(book.getTitle(), book.getAuthor(), repoFile) == null) {
            List<Book> books = getAllFrom(repoFile);
            books.add(book);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(repoFile))) {
                oos.writeObject(books);
            } catch (IOException e) {
                throw new DAOException(e);
            }
        }
    }

    private File getFile(String path, String filename) {
        File dir = new File(path);
        if (!dir.isDirectory())
            dir.mkdirs();
        return new File(dir, filename);
    }
}