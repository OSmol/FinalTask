package by.javatr.lemesheuski.library.сontroller;

import by.javatr.lemesheuski.library.сontroller.Command.*;
import by.javatr.lemesheuski.library.сontroller.Command.impl.AddBook;
import by.javatr.lemesheuski.library.сontroller.Command.impl.GetBooks;
import by.javatr.lemesheuski.library.сontroller.Command.impl.Registration;
import by.javatr.lemesheuski.library.сontroller.Command.impl.SignIn;

public class Controller {
    private final CommandProvider provider = new CommandProvider();
    private final char paramDelimiter='&';

    public String executeTask(String request){
        String command = request.substring(0,request.indexOf(paramDelimiter)).toUpperCase();
        request = request.substring(request.indexOf(paramDelimiter)+1);
        String response = null;
        Command executionCommand;
        executionCommand = provider.getCommand(command);
        response = executionCommand.execute(request);
        return response;
    }
}
