package by.javatr.lemesheuski.library.dao.impl;

import by.javatr.lemesheuski.library.dao.UserDAO;
import by.javatr.lemesheuski.library.dao.exception.DAOException;
import by.javatr.lemesheuski.library.entity.User;
import by.javatr.lemesheuski.library.entity.exception.UserException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getAll() throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            File file = getFile("./resources", "user.odt");
            Object o;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while((o=ois.readObject())!=null){
                    users=(List<User>) o;
                }
            }catch (EOFException e){
            }
        }catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public User findUserByLogin(String login) throws DAOException {
        List<User> users = getAll();
        for (User u : users) {
            if (u.getLogin().equals(login))
                return u;
        }
        return null;
    }

    @Override
    public boolean addUser(String login, String password) throws DAOException {
        String type;
        if (getAll().isEmpty())
            type = "admin";
        else type = "user";
        try {
            User user = new User(login, password, type);
            return addUser(user);
        } catch (UserException e) {
            throw new DAOException("Object create exception " + e.getMessage());
        }
    }

    private boolean addUser(User user) throws DAOException {
        if (findUserByLogin(user.getLogin()) == null) {
            try {
                List<User> users= getAll();
                users.add(user);
                File file = getFile("./resources", "user.odt");
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(users);
                }
            }catch (IOException e) {
                throw new DAOException(e.getMessage(), e);
            }
            return true;
        }
        return false;
    }

    private File getFile(String path, String filename) throws IOException{
        File dir = new File(path);
        if (!dir.isDirectory())
            dir.mkdirs();
        File file = new File(dir, filename);
        if (!file.exists())
            file.createNewFile();
        return file;
    }
}
