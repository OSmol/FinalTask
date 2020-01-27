package by.javatr.lemesheuski.library.entity.exception;

public class BookException extends Exception {
    public BookException(String message){
        super(message);
    }

    public BookException(String message, Exception e){
        super(message, e);
    }
}
