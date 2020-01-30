package by.javatr.lemesheuski.library.сontroller.Command.impl;

import by.javatr.lemesheuski.library.сontroller.Command.Command;

public class Logout implements Command {

    @Override
    public String execute(String request) {
        String response="&&You have successfully logged out";
        return response;
    }
}
