package by.javatr.lemesheuski.library.сontroller.Command.impl;

import by.javatr.lemesheuski.library.service.BookService;
import by.javatr.lemesheuski.library.service.ServiceFactory;
import by.javatr.lemesheuski.library.service.exception.ServiceException;
import by.javatr.lemesheuski.library.сontroller.Command.Command;

public class GetBooks implements Command {
    @Override
    public String execute(String request) {
        String response;
        String[] requestParams = request.split("&");
        String type = "";
        if(requestParams.length != 0) {
            type = requestParams[0];
        }
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();
        try {
            response = type + "&" + bookService.getAllBooks();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            response = "&Error while getting list of books";
        }
        return response;

    }
}
