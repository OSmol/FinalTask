package by.javatr.lemesheuski.library.entity;

import by.javatr.lemesheuski.library.entity.exception.UserException;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private String type;

    public String getLogin() {
        return login;
    }

    public boolean setLogin(String login) {
        String loginPattern = "[a-zA-Z1-90]{5,20}";
        if (login != null && login.matches(loginPattern)) {
            this.login = login;
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        String passwordPattern="[a-zA-Z1-90]{4,20}";
        if(password != null && password.matches(passwordPattern)) {
            this.password = password;
            return true;
        }
        return false;
    }

    public String getType() {
        return type;
    }

    public boolean setType(String type) {
        if(type.equals("admin") || type.equals("user")) {
            this.type = type;
            return true;
        }
        return false;
    }

    public User(String login, String password, String type)
    throws UserException {
        if(login == null)
            throw new UserException("Login is empty");
        if(password == null)
            throw new UserException("Password is empty");
        if(type == null)
            throw new UserException("Type is empty");
        if(!setLogin(login))
            throw new UserException("Login is not correct");
        if(!setPassword(password))
            throw new UserException("Password is not correct");
        if(!setType(type))
            throw new UserException("Type is not correct");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return type != null ? type.equals(user.type) : user.type == null;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass() +
                " login=" + login +
                " password=" + password +
                " type=" + type + '\'';
    }
}
