package by.javatr.lemesheuski.library.service.exception;

public class ServiceException extends Exception {

    public ServiceException(){
        super();
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Exception e){
        super(message, e);
    }

    public ServiceException(Exception e){
        super(e);
    }
}
