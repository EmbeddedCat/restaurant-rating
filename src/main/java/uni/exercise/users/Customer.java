package uni.exercise.users;

import uni.exercise.security.SecurityManager;
import uni.exercise.users.user_exceptions.UserNotFound;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Customer extends User {
    @Override
    public void login(String username, String pass) throws UserNotFound {
        try {
            this.getCredentials(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String hashedPassword = null;
        try {
            String saltedPassword = pass + this.salt;
            hashedPassword = SecurityManager.getHash(saltedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if (!(username.equals(this.username) && hashedPassword.equals(this.password))) {
            throw new UserNotFound("Login Failed...");
        }
    }
}
