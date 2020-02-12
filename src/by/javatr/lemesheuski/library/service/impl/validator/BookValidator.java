package by.javatr.lemesheuski.library.service.impl.validator;

import java.util.List;

public class BookValidator {
    public boolean isValidTitle(String title) {
        if (title == null) {
            return false;
        }
        return true;
    }

    public boolean isValidAuthor(String author) {
        if (author == null) {
            return false;
        }
        return true;
    }

    public boolean isValidGenre(List<String> genres) {
        String pattern = "[А-Я][а-я]{0,19}";
        if (genres == null || genres.isEmpty()) {
            return false;
        }
        for (String genre: genres) {
            if(genre.matches(pattern)){
                return true;
            }
        }
        return false;
    }

    public boolean isValidYear(int year) {
        if (year > 1800 && year < 2021) {
            return true;
        }
        return false;
    }

    public boolean isValidAnnotation(String annotation) {
        if (annotation == null || annotation.length() < 200) {
            return false;
        }
        return true;
    }
}
