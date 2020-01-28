package by.javatr.lemesheuski.library.сontroller;

import by.javatr.lemesheuski.library.сontroller.Command.Command;
import by.javatr.lemesheuski.library.сontroller.Command.SignIn;

public class Controller {
    private final char paramDelimiter='&';

    public String executeTask(String request){
        String command = request.substring(0,request.indexOf(paramDelimiter));
        command = command.toUpperCase();
        String response = null;
        Command executionCommand;
        switch (command){
            case "SIGN_IN":
                executionCommand = new SignIn();
                response = executionCommand.execute(request);
                break;
            case "REGISTER":
                break;
            case "ADD_BOOK":
                break;
            case "GET_BOOKS":
                break;
            case "LOGOUT":
                response="&You have successfully logged out";
                break;
            case "EXIT":
                response="exit";
                break;
            default:
                response = "Could not found this command";
        }
        return response;
    }
}
