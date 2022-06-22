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
        HashMap<String, String> dbCredentials;

        try {
            dbCredentials = QueryManager.getFromDatabase(
                    username,
                    Queries.RETRIEVE_DETAILS.get_query(),
                    DBConnection.getConnection(),
                    "rest_user",
                    "username",
                    "password"
            );
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String fromDBUsername = dbCredentials.get("username");
        String fromDBPassword = dbCredentials.get("password");
        String hashedPassword = fromDBPassword; // TODO - make it secure.

        if (!(username.equals(fromDBUsername) && hashedPassword.equals(fromDBPassword))) {
            throw new UserNotFound("Login Failed...");
        }
    }

    @Override
    public void logout(HttpSession session) {

    }
}
