package uni.exercise.users;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;
import uni.exercise.users.user_exceptions.FailedToLogin;
import uni.exercise.users.user_exceptions.UserNotFound;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;

public class Customer extends User {


    @Override
    public void login(String username, String pass) throws UserNotFound,
                                                           FailedToLogin {
        try {
            this.getCredentials(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String hashedPassword = this.password; // TODO - make it secure.

        if (!(username.equals(this.username) && hashedPassword.equals(this.password))) {
            throw new UserNotFound("Login Failed...");
        }
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
