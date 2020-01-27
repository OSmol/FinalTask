package by.javatr.lemesheuski.library.сontroller;

public class Controller {
    private final char paramDelimiter='&';

    public String executeTask(String request){
        String command = request.substring(0,request.indexOf(paramDelimiter));
        command = command.toUpperCase();
        String response = null;
        switch (command){
            case "SIGN_IN":
                break;
            case "REGISTER":
                break;
            case "ADD_BOOK":
                break;
            case "GET_BOOKS":
                break;
            default:
                response = "Could not found this command";
        }
        return response;
    }
}
