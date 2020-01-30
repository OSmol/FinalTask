package by.javatr.lemesheuski.library.сontroller.Command.impl;

import by.javatr.lemesheuski.library.service.BookService;
import by.javatr.lemesheuski.library.service.ServiceFactory;
import by.javatr.lemesheuski.library.service.exception.ServiceException;
import by.javatr.lemesheuski.library.сontroller.Command.Command;

public class GetFavoriteBooks implements Command {

    @Override
    public String execute(String request) {
        String response;
        String[] requestParams = request.split("&");
        String type = "";
        String username = "";
        if (requestParams.length >= 2) {
            type = requestParams[0];
            username = requestParams[1];
        }
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();
        if (!username.equals("")) {
            try {
                response = type + "&" + username + "&Books:\n" + bookService.getFavoriteBooks(username);
            } catch (ServiceException e) {
                response = type + "&" + username + "&Error while getting list of books: " + e.getMessage();
            }
        }else{
            response = type + "&" + username +"&You have not favorite books: ";
        }
        return response;
    }
}
