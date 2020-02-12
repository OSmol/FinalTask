package by.javatr.lemesheuski.library.сontroller.Command;

import by.javatr.lemesheuski.library.сontroller.Command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    public final Map<CommandName, Command> repository = new HashMap<>();
    public CommandProvider(){
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.ADD_BOOK, new AddBook());
        repository.put(CommandName.GET_BOOKS, new GetBooks());
        repository.put(CommandName.DELETE_BOOK, new DeleteBook());
        repository.put(CommandName.REMOVE_BOOK_FROM_FAVORITE, new DeleteBookFromFavorite());
        repository.put(CommandName.MARK_AS_FAVORITE, new AddBookToFavorite());
        repository.put(CommandName.GET_FAVORITE_BOOKS, new GetFavoriteBooks());
        repository.put(CommandName.EXIT, new Exit());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        repository.put(CommandName.LOGOUT, new Logout());
    }

    public Command getCommand(String name){
        CommandName commandName;
        Command command;
        if (name != null) {
            try {
                commandName = CommandName.valueOf(name.toUpperCase());
                command = repository.get(commandName);
            } catch (IllegalArgumentException e) {
                command = repository.get(CommandName.WRONG_REQUEST);
            }
        }else {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
