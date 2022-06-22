package uni.exercise.users;

import uni.exercise.db.DBConnection;
import uni.exercise.db.QueryManager;
import uni.exercise.users.user_exceptions.FailedToLogin;
import uni.exercise.users.user_exceptions.UserNotFound;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class User {

    // This method is responsible for user LoginServlet.
    abstract public void login(String username, String pass) throws FailedToLogin,
                                                                    UserNotFound;

    public HashMap<String, String> getUserDetails(String username) throws SQLException {
        return QueryManager.getFromDatabase(
                username,
                "SELECT username, password FROM rest_users WHERE username='"+username+"'", // TODO - φτιαξτο
                DBConnection.getConnection(),
                "rest_users",
                "username",
                "password"
        );
    }

    // This method is responsible for user logout.
    abstract public void logout(HttpSession session);
}
