package by.javatr.lemesheuski.library.dao;

import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.Book;
import by.javatr.lemesheuski.library.entity.exception.BookException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookDAO {
    public static List<Book> getAll() throws DAOException {
        List<Book> books = new ArrayList<>();
        try {
            File file = new File("./books.txt");
            if (!file.exists())
                file.createNewFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                String[] lines = new String[5];
                while ((line = reader.readLine()) != null) {
                    lines[0] = line.trim();
                    for (int i = 1; i < 5; i++) {
                        lines[i] = reader.readLine().trim();
                    }
                    books.add(new Book(lines[0], lines[1], Integer.parseInt(lines[2]), lines[4], Arrays.asList(lines[3].trim().split(" "))));
                }
            }
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        } catch (BookException e) {
            throw new DAOException("Object create exception" + e.getMessage());
        }
        return books;
    }

    public static Book findBookByBook(Book book) throws DAOException {
        try {
            File file = new File("./books.txt");
            if (!file.exists())
                file.createNewFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                String[] lines = new String[5];
                while ((line = reader.readLine()) != null) {
                    lines[0] = line.trim();
                    for (int i = 1; i < 5; i++) {
                        lines[i] = line.trim();
                    }
                    if (new Book(lines[0], lines[1], Integer.parseInt(lines[2]), lines[3], Arrays.asList(lines[4].split(" "))).equals(book))
                        return book;
                }
            }
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        } catch (BookException e) {
            throw new DAOException("Object create exception" + e.getMessage());
        }
        return null;
    }

    public static boolean addBook(String title, String author, int year, String annotation, List<String> genre)
            throws DAOException {
        try {
            Book book = new Book(title, author, year, annotation, genre);

            if (findBookByBook(book) == null) {
                try {
                    File file = new File("./books.txt");
                    if (!file.exists())
                        file.createNewFile();
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                        writer.write(book.toString());
                    }
                } catch (IOException e) {
                    return false;
                }
                return true;
            }
        } catch (BookException e) {
            throw new DAOException("Object create exception" + e.getMessage());
        }
        return false;
    }
}
