package by.javatr.lemesheuski.library.dao.impl;

import by.javatr.lemesheuski.library.dao.BookDAO;
import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.Book;
import by.javatr.lemesheuski.library.entity.exception.BookException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> getAll() throws DAOException {
        List<Book> books = new ArrayList<>();
        try {
            File file = getFile("./resources", "books.odt");
            Object o;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while ((o = ois.readObject()) != null) {
                    books = (List<Book>) o;
                }
            } catch (EOFException e) {
            }
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return books;
    }

    @Override
    public Book findBookByTitleAndAuthor(String title, String author) throws DAOException {
        List<Book> books = getAll();
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author))
                return book;
        }
        return null;
    }

    @Override
    public void addBookToFavorite(String username, String title, String author) throws DAOException {
        List<Book> favoriteBooks = getFavoriteBooks(username);
        boolean favorite = false;
        for (Book book : favoriteBooks) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author))
                favorite = true;
        }
        if (favorite == false) {
            List<Book> books = getAll();
            for (Book book : books) {
                if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                    try {
                        favoriteBooks.add(book);
                        File file = getFile("./resources/favorites", username + ".odt");
                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                            oos.writeObject(favoriteBooks);
                        }
                    } catch (IOException e) {
                        throw new DAOException(e.getMessage());
                    }
                }
            }
        } else
            throw new DAOException("You already added this book");
    }

    @Override
    public List<Book> getFavoriteBooks(String username) throws DAOException {
        List<Book> books = new ArrayList<>();
        try {
            File file = getFile("./resources/favorites", username + ".odt");
            Object o;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while ((o = ois.readObject()) != null) {
                    books = (List<Book>) o;
                }
            } catch (EOFException e) {
            }
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return books;
    }

    @Override
    public void deleteBookFromFavorites(String username, String title, String author)
            throws DAOException {
        List<Book> books = getFavoriteBooks(username);
        Book removable = null;
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author))
                removable = book;
        }
        if(removable!=null) {
            books.remove(removable);
            try {
                File file = getFile("./resources/favorites", username + ".odt");
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(books);
                }
            } catch (IOException e) {
                throw new DAOException(e.getMessage());
            }
        }else
            throw new DAOException("There is no such book");
    }

    @Override
    public void deleteBook(String title, String author)
            throws DAOException {
        List<Book> books = getAll();
        Book removable = findBookByTitleAndAuthor(title, author);
        if(removable!=null) {
            books.remove(removable);
            try {
                File file = getFile("./resources", "books.odt");
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(books);
                }
            } catch (IOException e) {
                throw new DAOException(e.getMessage());
            }
        }else{
            throw new DAOException("Do not found this book");
        }
    }

    @Override
    public void addBook(String title, String author, int year, String annotation, List<String> genre)
            throws DAOException {
        try {
            Book book = new Book(title, author, year, annotation, genre);
            List<Book> books = getAll();
            books.add(book);
            if (findBookByTitleAndAuthor(book.getTitle(), book.getAuthor()) == null) {
                try {
                    File file = getFile("./resources", "books.odt");
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                        oos.writeObject(books);
                    }
                } catch (IOException e) {
                    throw new DAOException(e.getMessage());
                }
            }
        } catch (BookException e) {
            throw new DAOException("Object create exception" + e.getMessage());
        }
    }

    private File getFile(String path, String filename) throws IOException {
        File dir = new File(path);
        if (!dir.isDirectory())
            dir.mkdirs();
        File file = new File(dir, filename);
        if (!file.exists())
            file.createNewFile();
        return file;
    }
}
