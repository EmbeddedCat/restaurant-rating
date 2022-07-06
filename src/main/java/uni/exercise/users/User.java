package uni.exercise.users;

import uni.exercise.db.DBConnection;
import uni.exercise.db.QueryManager;
import uni.exercise.users.user_exceptions.FailedToLogin;
import uni.exercise.users.user_exceptions.UserNotFound;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class User {
    protected String username;
    protected String password;
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

    // This method is responsible for user LoginServlet.
    abstract public void login(String username, String pass) throws FailedToLogin,
                                                                    UserNotFound;

    // This method is responsible for user logout.
    abstract public void logout(HttpSession session);
}
