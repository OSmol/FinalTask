package by.javatr.lemesheuski.library.service.impl;

import by.javatr.lemesheuski.library.dao.DAOFactory;
import by.javatr.lemesheuski.library.dao.UserDAO;
import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.User;
import by.javatr.lemesheuski.library.service.UserService;
import by.javatr.lemesheuski.library.service.exception.ServiceException;
import by.javatr.lemesheuski.library.service.impl.validator.UserValidator;

public class UserServiceImpl implements UserService {
    UserDAO userDAO = DAOFactory.getInstance().getUserService();
    private UserValidator userValidator = new UserValidator();

    @Override
    public User signIn(String login, String password) throws ServiceException{
        if(userValidator.isLoginValid(login) && userValidator.isPasswordValid(password)){
            try {
                User user =  userDAO.findUserByLoginAndPassword(login, password);
                if(user!=null) {
                        return user;
                }
            }catch (DAOException e){
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public boolean register(String login, String password) throws ServiceException{
        if(userValidator.isLoginValid(login) && userValidator.isPasswordValid(password)){
            User user = new User(login, password);
            try {
                return userDAO.addUser(user);
            }catch (DAOException e){
                throw new ServiceException(e);
            }
        }else{
            return false;
        }
    }
}
