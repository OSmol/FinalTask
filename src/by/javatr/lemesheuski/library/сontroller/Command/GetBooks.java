package by.javatr.lemesheuski.library.сontroller.Command;

import by.javatr.lemesheuski.library.service.BookService;
import by.javatr.lemesheuski.library.service.ServiceFactory;
import by.javatr.lemesheuski.library.service.exception.ServiceException;

public class GetBooks implements Command {
    @Override
    public String execute(String request) {
        String response;
        String[] requestParams = request.split("&");
        String type = requestParams[0];
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();
        try {
            response = type + "&"+bookService.getAllBooks();
        } catch (ServiceException e) {
            response = "&Error while getting list of books";
        }
        return response;
    }
}
