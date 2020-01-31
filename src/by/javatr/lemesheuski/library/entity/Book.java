package by.javatr.lemesheuski.library.entity;

import by.javatr.lemesheuski.library.entity.exception.BookException;

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

    public boolean setTitle(String title) {
        if (title != null) {
            this.title = title;
            return true;
        }
        return false;
    }

    public String getAuthor() {
        return author;
    }

    public boolean setAuthor(String author) {
        if(author != null) {
            this.author = author;
            return true;
        }
        return false;
    }

    public int getYear() {
        return year;
    }

    public boolean setYear(int year) {
        if (year > 1800 && year < 2020) {
            this.year = year;
            return true;
        }
        return false;
    }

    public String getAnnotation() {
        return annotation;
    }

    public boolean setAnnotation(String annotation) {
        if(annotation!=null){
            if(annotation.length()>20 && annotation.length()<1000){
                this.annotation = annotation;
                return true;
            }
        }
        return false;
    }

    public List<String> getGenres() {
        List<String> tmp = new ArrayList<>();
        for (String genre:genres) {
            tmp.add(genre);
        }
        return tmp;
    }

    public boolean setGenres(List<String> genres) {
        if(!genres.isEmpty()){
            this.genres = genres;
            return true;
        }
        return false;
    }

    public Book(String title, String author, int year,
                String annotation, List<String> genres)
    throws BookException {
        if(title == null)
            throw new BookException("Title is empty");
        if(author == null)
            throw new BookException("Author is empty");
        if(annotation == null)
            throw new BookException("Annotation is empty");
        if(genres == null)
            throw new BookException("Genres is empty");
        if(!setTitle(title))
            throw new BookException("Incorrect title");
        if(!setAuthor(author))
            throw new BookException("Incorrect author");
        if(!setYear(year))
            throw new BookException("Incorrect year");
        if(!setAnnotation(annotation))
            throw new BookException("Incorrect annotation");
        if(!setGenres(genres))
            throw new BookException("Incorrect genres");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if(getClass() != o.getClass()) return false;

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
        str.append("\n"+this.getTitle()+"\n"+this.getAuthor()+"\n"+this.getYear()+"\n");
        String genres ="";
        for(String genre:this.getGenres())
            genres+=genre+" ";
        str.append(genres.trim()+"\n    ");
        String[] annotation = this.getAnnotation().split(" ");
        int symbolCounter = 0;
        int index = 0;
        while(index != annotation.length){
            if(symbolCounter+annotation[index].length()<=150){
                symbolCounter += annotation[index].length()+1;
                str.append(annotation[index]).append(" ");
                index++;
            }else {
                str.append("\n");
                symbolCounter=0;
            }
        }
        str.append("\n");
        return str.toString();
    }
}
