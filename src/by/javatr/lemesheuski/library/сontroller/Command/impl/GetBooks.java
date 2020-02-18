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
        String username = "";
        if (requestParams.length >= 2) {
            type = requestParams[0];
            username = requestParams[1];
        }
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();
        try {
            response = type + "&" + username + "&Books:\n" + bookService.getAllBooks();
        } catch (ServiceException e) {
            response = type + "&" + username +"&Error while getting list of books: " + e.getMessage();// исключения пишутся в лог, а не отправляются клиенту. Читай дорогой клиент - радуйся, знай, как там у нас все устроено
        }
        return response;
    }
}
