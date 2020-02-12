package by.javatr.lemesheuski.library.service.impl.validator;

public class UserValidator {
    public boolean isLoginValid(String login){
        String loginPattern = "[a-zA-Z1-90]{5,20}";
        if(login!=null) {
            if(login.matches(loginPattern)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPasswordValid(String password){
        String passwordPattern = "[a-zA-Z1-90]{4,20}";
        if(password!=null) {
            if(password.matches(passwordPattern)) {
                return true;
            }
        }
        return false;
    }
}
