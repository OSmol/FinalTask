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
        if (requestParams.length != 0) {
            type = requestParams[0];
        }
        if (type.equals("admin")) {
            if (requestParams.length == 6) {
                String title = requestParams[1];
                String author = requestParams[2];
                try {
                    int year = Integer.parseInt(requestParams[3]);
                    List<String> genres = Arrays.asList(requestParams[4].split(" "));
                    String annotation = requestParams[5];
                    ServiceFactory serviceFactory = ServiceFactory.getInstance();
                    BookService bookService = serviceFactory.getBookService();
                    try {
                        if (bookService.addBook(title, author, year, genres, annotation)) {
                            response = type + "&Book successfully added";
                        } else
                            response = type + "&Adding error";
                    } catch (ServiceException e) {
                        System.out.println(e.getMessage());
                        response = type + "&Adding error";
                    }
                } catch (NumberFormatException e) {
                    response = type + "&Illegal parameters";
                }
            } else
                response = type + "&Illegal parameters";
        } else {
            response = type + "&You are not administrator";
        }
        return response;
    }
}
