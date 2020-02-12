package by.javatr.lemesheuski.library.сontroller.Command.impl;


import by.javatr.lemesheuski.library.entity.User;
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
        String username = "";
        if (requestParams.length >= 2) {
            type = requestParams[0];
            username = requestParams[1];
        }
        if (type.equals("")) {
            if (requestParams.length == 4) {
                String login = requestParams[2];
                String password = requestParams[3];
                ServiceFactory serviceFactory = ServiceFactory.getInstance();
                UserService userService = serviceFactory.getUserService();
                try {
                    User user = userService.signIn(login, password);
                    if (user != null)
                        response = user.getType() + "&"+ user.getLogin() + "&You are successfully logged in as " + user.getType();
                    else
                        response = "&&Wrong login or password";
                } catch (ServiceException e) {
                    response = "&&Authorization error: "+e.getMessage();
                }
            } else
                response = "&&Illegal parameters";
        } else {
            response = type + "&" + username + "&You are already logged in";
        }
        return response;
    }
}
