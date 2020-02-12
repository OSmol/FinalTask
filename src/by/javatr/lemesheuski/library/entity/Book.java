package by.javatr.lemesheuski.library.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
    String title;
    String author;
    int year;
    String annotation;
    List<String> genres;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public List<String> getGenres() {
        List<String> tmp = new ArrayList<>();
        for (String genre : genres) {
            tmp.add(genre);
        }
        return tmp;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Book(String title, String author, int year,
                String annotation, List<String> genres) {
        setTitle(title);
        setAuthor(author);
        setYear(year);
        setAnnotation(annotation);
        setGenres(genres);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (year != book.year) return false;
        if (!title.equals(book.title)) return false;
        if (!author.equals(book.author)) return false;
        if (!annotation.equals(book.annotation)) return false;
        return genres.equals(book.genres);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + year;
        result = 31 * result + annotation.hashCode();
        result = 31 * result + genres.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n" + this.getTitle() + "\n" + this.getAuthor() + "\n" + this.getYear() + "\n");
        String genres = "";
        for (String genre : this.getGenres())
            genres += genre + " ";
        str.append(genres.trim() + "\n    ");
        String[] annotation = this.getAnnotation().split(" ");
        int symbolCounter = 0;
        int index = 0;
        while (index != annotation.length) {
            if (symbolCounter + annotation[index].length() <= 150) {
                symbolCounter += annotation[index].length() + 1;
                str.append(annotation[index]).append(" ");
                index++;
            } else {
                if(annotation[index].length()>150){
                    str.append(annotation[index]).append(" ");
                    index++;
                }
                str.append("\n");
                symbolCounter = 0;
            }
        }
        str.append("\n");
        return str.toString();
    }
}
