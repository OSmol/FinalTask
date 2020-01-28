package by.javatr.lemesheuski.library.сontroller.Command;

import by.javatr.lemesheuski.library.service.BookService;
import by.javatr.lemesheuski.library.service.ServiceFactory;
import by.javatr.lemesheuski.library.service.exception.ServiceException;

import java.util.Arrays;
import java.util.List;

public class AddBook implements Command {
    @Override
    public String execute(String request) {
        String response;
        String[] requestParams = request.split("&");
        String type = requestParams[0];
        String title = requestParams[1];
        String author = requestParams[2];
        try {
            int year = Integer.parseInt(requestParams[3]);
            String annotation = requestParams[4];
            List<String> genres = Arrays.asList(requestParams[5].split(" "));
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            BookService bookService = serviceFactory.getBookService();
            if (type.equals("admin")) {
                try {
                    if (bookService.addBook(title, author, year, annotation, genres)) {
                        response = type + "&Book successfully added";
                    } else
                        response = type + "&Adding error";
                } catch (ServiceException e) {
                    response = type + "&Adding error";
                }
            } else {
                response = type + "&You are not administrator";
            }
        }catch (NumberFormatException e){
            response="Illegal argument";
        }
        return response;
    }
}
