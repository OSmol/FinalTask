package by.javatr.lemesheuski.library.сontroller.Command.impl;

import by.javatr.lemesheuski.library.service.BookService;
import by.javatr.lemesheuski.library.service.ServiceFactory;
import by.javatr.lemesheuski.library.service.exception.ServiceException;
import by.javatr.lemesheuski.library.сontroller.Command.Command;

import java.util.Arrays;
import java.util.List;

public class AddBook implements Command {
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
        if (type.equals("admin")) {
            if (requestParams.length == 7) {
                String title = requestParams[2];
                String author = requestParams[3];
                try {
                    int year = Integer.parseInt(requestParams[4]);
                    List<String> genres = Arrays.asList(requestParams[5].split(" "));
                    String annotation = requestParams[6];
                    ServiceFactory serviceFactory = ServiceFactory.getInstance();
                    BookService bookService = serviceFactory.getBookService();
                    try {
                        bookService.addBook(title, author, year, genres, annotation);
                        response = type + "&" + username + "&Book successfully added";
                    } catch (ServiceException e) {
                        response = type + "&" + username + "&Adding error " + e.getMessage();
                    }
                } catch (NumberFormatException e) {
                    response = type + "&" + username + "&Illegal parameters";
                }
            } else
                response = type + "&" + username + "&Illegal parameters";
        } else {
            response = type + "&" + username + "&You are not administrator";
        }
        return response;
    }
}
