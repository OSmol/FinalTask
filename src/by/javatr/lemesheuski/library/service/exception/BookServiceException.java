package by.javatr.lemesheuski.library.service.exception;

public class BookServiceException extends ServiceException {
    public BookServiceException(String message){
        super(message);
    }

    public BookServiceException(String message, Exception e){
        super(message, e);
    }
}
