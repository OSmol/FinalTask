package by.javatr.lemesheuski.library.dao;

import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.User;
import by.javatr.lemesheuski.library.entity.exception.UserException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static List<User> getAll() throws DAOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./user.txt"))) {
            String line;
            String[] lines;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                lines = line.split(" ");
                if (lines.length == 3) {
                    users.add(new User(lines[0], lines[1], lines[2]));
                }
            }
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        } catch (UserException e) {
            throw new DAOException("Object create exception" + e.getMessage());
        }
        return users;
    }

    public static User findUserByLogin(String login) throws DAOException {
        List<User> users = getAll();
        for (User u : users) {
            if (u.getLogin() == login)
                return u;
        }
        return null;
    }

    public static boolean addUser(String login, String password) throws DAOException {
        String type;
        if (getAll().isEmpty())
            type = "admin";
        else type = "user";
        try {
            User user = new User(login, password, type);
            return addUser(user);
        } catch (UserException e) {
            throw new DAOException("Object create exception" + e.getMessage());
        }
    }

//    public static boolean addUser(String login, String password, String type) throws DAOException {
//        try {
//            User user = new User(login, password, type);
//            return addUser(user);
//        } catch (UserException e) {
//            throw new DAOException("Object create exception" + e.getMessage());
//        }
//    }

    private static boolean addUser(User user) throws DAOException {
        if (findUserByLogin(user.getLogin()) == null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("./user.txt", true))) {
                writer.write(user.getLogin() + " " + user.getLogin() + " " + user.getType());
            } catch (IOException e) {
                throw new DAOException(e.getMessage(), e);
            }
            return true;
        }
        return false;
    }
}
