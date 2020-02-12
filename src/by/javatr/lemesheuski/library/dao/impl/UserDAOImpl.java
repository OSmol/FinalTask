package by.javatr.lemesheuski.library.dao.impl;

import by.javatr.lemesheuski.library.dao.UserDAO;
import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private String rootDir = "./resources";
    private String userRepo = "user";
    private String fileExt = ".odt";

    private List<User> getAll() throws DAOException {
        List<User> users = new ArrayList<>();
        File file = getFile(rootDir, userRepo + fileExt);
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object o;
            while (fis.available() > 0) {
                if ((o = ois.readObject()) != null) {
                    if (users.getClass() == o.getClass()) {
                        users = (List<User>) o;
                    }
                }
            }
        } catch (FileNotFoundException e){
            return users;
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        List<User> users = getAll();
        for (User u : users) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password))
                return u;
        }
        return null;
    }

    private User findUserByLogin(String login) throws DAOException {
        List<User> users = getAll();
        for (User u : users) {
            if (u.getLogin().equals(login))
                return u;
        }
        return null;
    }

    @Override
    public boolean addUser(User user) throws DAOException {
        String type;
        List<User> users = getAll();
        if (users.isEmpty())
            type = "admin";
        else type = "user";
        user.setType(type);
        if (findUserByLogin(user.getLogin()) == null) {
            File file = getFile(rootDir, userRepo + fileExt);
            users.add(user);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(users);
            } catch (IOException e) {
                throw new DAOException(e.getMessage(), e);
            }
            return true;
        }
        return false;
    }

    private File getFile(String path, String filename) {
        File dir = new File(path);
        if (!dir.isDirectory())
            dir.mkdirs();
        return new File(dir, filename);
    }
}
