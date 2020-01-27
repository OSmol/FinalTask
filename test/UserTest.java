import by.javatr.lemesheuski.library.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void setPasswordTest_lessThen4(){
        String expPass="111111";
        User user = new User("1111111", "111111", "admin");
        user.setPassword("uId");
        Assertions.assertEquals(expPass, user.getPassword());
    }

    @Test
    public void setPasswordTest_moreThen20(){
        String expPass="111111";
        User user = new User("111111", "111111", "admin");
        user.setPassword("HereIsMoreThenTwentyIGuess");
        Assertions.assertEquals(expPass, user.getPassword());
    }

    @Test
    public void setLoginTest(){
        String expLogin="MainAdm";
        User user = new User("1111111", "111111", "admin");
        user.setLogin("MainAdm");
        Assertions.assertEquals(expLogin, user.getLogin());
    }
}
