package by.javatr.lemesheuski.library.сontroller.Command.impl;

import by.javatr.lemesheuski.library.сontroller.Command.Command;

public class WrongRequest implements Command {
    @Override
    public String execute(String request) {
        String response = request.substring(0,request.indexOf('&')) + "&Could not found this command";
        return response;
    }
}
