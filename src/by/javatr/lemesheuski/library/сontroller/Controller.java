package by.javatr.lemesheuski.library.сontroller;

import by.javatr.lemesheuski.library.сontroller.Command.*;

public class Controller {
    private final char paramDelimiter='&';

    public String executeTask(String request){
        String command = request.substring(0,request.indexOf(paramDelimiter));
        request = request.substring(request.indexOf(paramDelimiter)+1);
        command = command.toUpperCase();
        String response = null;
        Command executionCommand;
        switch (command){
            case "SIGN_IN":
                executionCommand = new SignIn();
                response = executionCommand.execute(request);
                break;
            case "REGISTER":
                executionCommand = new Register();
                response = executionCommand.execute(request);
                break;
            case "ADD_BOOK":
                executionCommand = new AddBook();
                response = executionCommand.execute(request);
                break;
            case "GET_BOOKS":
                executionCommand = new GetBooks();
                response = executionCommand.execute(request);
                break;
            case "LOGOUT":
                response="&You have successfully logged out";
                break;
            case "EXIT":
                response="exit";
                break;
            default:
                response = request.substring(0,request.indexOf(paramDelimiter)) + "&Could not found this command";
        }
        return response;
    }
}
