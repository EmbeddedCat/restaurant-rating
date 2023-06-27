package uni.exercise.users;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;
import uni.exercise.users.user_exceptions.UserNotFound;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class User {
    protected String username;
    protected String password;
    protected String salt;
    protected String address;
    protected String mail;

    public User() {
        this.username = null;
        this.password = null;
        this.mail     = null;
        this.address  = null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getMail() {
        return mail;
    }

    public void getCredentials(String username) throws SQLException {
        HashMap<String, Object> dbCredentials;
        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();

        dbCredentials = queryManager.getFromDatabase(
                username,
                Queries.RETRIEVE_DETAILS.getQuery(),
                dbConnection.getConnection(),
                "rest_user",
                "username",
                "password",
                "salt",
                "address",
                "email"
        );

        this.username = (String) dbCredentials.get("username");
        this.password = (String) dbCredentials.get("password");
        this.salt     = (String) dbCredentials.get("salt");
        this.mail     = (String) dbCredentials.get("email");
        this.address  = (String) dbCredentials.get("address");
        dbConnection.closeConnection();
    }

    // This method is responsible for user LoginServlet.
    abstract public void login(String username, String pass) throws UserNotFound;

    // This method is responsible for user logout.
    public static void logout(HttpSession session) {
        session.invalidate();
    }
}
