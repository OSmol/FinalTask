package by.javatr.lemesheuski.library.сontroller.Command.impl;


import by.javatr.lemesheuski.library.service.ServiceFactory;
import by.javatr.lemesheuski.library.service.UserService;
import by.javatr.lemesheuski.library.service.exception.ServiceException;
import by.javatr.lemesheuski.library.сontroller.Command.Command;

public class SignIn implements Command {
    @Override
    public String execute(String request) {
        String response;
        String[] requestParams = request.split("&");
        String type = "";
        if (requestParams.length != 0) {
            type = requestParams[0];
        }
        if (type.equals("")) {
            if (requestParams.length == 3) {
                String login = requestParams[1];
                String password = requestParams[2];
                ServiceFactory serviceFactory = ServiceFactory.getInstance();
                UserService userService = serviceFactory.getUserService();
                try {
                    type = userService.signIn(login, password);
                    if (type != null)
                        response = type + "&You are successfully logged in as " + type;
                    else
                        response = "&Wrong login or password";
                } catch (ServiceException e) {
                    response = "&Authorization error";
                }

            } else
                response = "&Illegal parameters";
        } else {
            response = "&You are already logged in";
        }
        return response;
    }
}
