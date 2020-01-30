package by.javatr.lemesheuski.library.сontroller.Command.impl;

import by.javatr.lemesheuski.library.service.BookService;
import by.javatr.lemesheuski.library.service.ServiceFactory;
import by.javatr.lemesheuski.library.service.exception.ServiceException;
import by.javatr.lemesheuski.library.сontroller.Command.Command;

public class AddBookToFavorite implements Command {
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
        if (!type.equals("")) {
            if (requestParams.length == 4) {
                String title = requestParams[2];
                String author = requestParams[3];
                ServiceFactory serviceFactory = ServiceFactory.getInstance();
                BookService bookService = serviceFactory.getBookService();
                try {
                    bookService.addBookToFavorite(username, title, author);
                    response = type + "&" + username + "&Book successfully added to favorite";
                } catch (ServiceException e) {
                    response = type + "&" + username + "&Adding error " + e.getMessage();
                }
            } else
                response = type + "&" + username + "&Illegal parameters";
        } else {
            response = type + "&" + username + "&You are not logged in";
        }
        return response;
    }
}
