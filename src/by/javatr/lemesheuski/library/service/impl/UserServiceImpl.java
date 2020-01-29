package by.javatr.lemesheuski.library.service.impl;

import by.javatr.lemesheuski.library.dao.UserDAO;
import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.User;
import by.javatr.lemesheuski.library.service.UserService;
import by.javatr.lemesheuski.library.service.exception.UserServiceException;

public class UserServiceImpl implements UserService {

    @Override
    public String signIn(String login, String password) throws UserServiceException{
        if(login!=null||password!=null){
            try {
                User user =  UserDAO.findUserByLogin(login);
                if(user!=null) {
                    if (user.getPassword().equals(password)) {
                        return user.getType();
                    }
                }
                return null;
            }catch (DAOException e){
                throw new UserServiceException(e.getMessage(), e);
            }
        }else{
            throw new UserServiceException("Login or password are empty");
        }
    }

    @Override
    public boolean register(String login, String password) throws UserServiceException{
        if(login!=null||password!=null){
            try {
                return UserDAO.addUser(login, password);
            }catch (DAOException e){
                throw new UserServiceException(e.getMessage(), e);
            }
        }else{
            throw new UserServiceException("Login or password are empty");
        }
    }
}
