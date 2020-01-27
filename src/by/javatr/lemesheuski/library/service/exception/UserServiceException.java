package by.javatr.lemesheuski.library.service.exception;

public class UserServiceException extends ServiceException {
    public UserServiceException(String message){
        super(message);
    }

    public UserServiceException(String message, Exception e){
        super(message, e);
    }
}
