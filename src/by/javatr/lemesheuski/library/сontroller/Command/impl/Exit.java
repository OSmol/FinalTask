package by.javatr.lemesheuski.library.сontroller.Command.impl;

import by.javatr.lemesheuski.library.сontroller.Command.Command;

public class Exit implements Command {

    @Override
    public String execute(String request) {
        String response="exit";
        return response;
    }
}
