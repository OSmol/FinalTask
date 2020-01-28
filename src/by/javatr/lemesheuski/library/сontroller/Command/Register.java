package by.javatr.lemesheuski.library.сontroller.Command;

import by.javatr.lemesheuski.library.service.ServiceFactory;
import by.javatr.lemesheuski.library.service.UserService;
import by.javatr.lemesheuski.library.service.exception.ServiceException;

public class Register implements Command {
    @Override
    public String execute(String request) {
        String response;
        String[] requestParams = request.split("&");
        String type = requestParams[0];
        String login = requestParams[1];
        String password = requestParams[2];
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        if(!type.equals("")){
            try {
                if(userService.register(login, password)){
                    response = "&You have successfully registered";
                }else
                    response = "&Registration error";
            }catch (ServiceException e){
                System.out.println(e.getMessage());
                response = "&Registration error";
            }
        }else{
            response = "&You are already logged in";
        }
        return response;
    }
}
