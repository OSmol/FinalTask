package by.javatr.lemesheuski.library.сontroller.Command.impl;

import by.javatr.lemesheuski.library.сontroller.Command.Command;

public class WrongRequest implements Command {
    @Override
    public String execute(String request) {
        String type = request.substring(0, request.indexOf('&'));
        request = request.substring(request.indexOf('&') + 1);
        String username = request.substring(0, request.indexOf('&'));
        String response = type + "&" + username + "&Could not found this command";
        return response;
    }
}
