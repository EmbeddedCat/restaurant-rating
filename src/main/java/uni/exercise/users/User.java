package uni.exercise.users;

import uni.exercise.users.user_exceptions.FailedToLogin;
import uni.exercise.users.user_exceptions.UserNotFound;

import javax.servlet.http.HttpSession;

public abstract class User {

    // This method is responsible for user LoginServlet.
    abstract public void login(String username, String pass) throws FailedToLogin,
                                                                    UserNotFound;

    // This method is responsible for user logout.
    abstract public void logout(HttpSession session);
}
