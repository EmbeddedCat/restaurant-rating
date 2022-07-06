package uni.exercise.users;

import uni.exercise.users.user_exceptions.FailedToLogin;
import uni.exercise.users.user_exceptions.UserNotFound;

import javax.servlet.http.HttpSession;

public class RestaurantOwner extends User {
    @Override
    public void login(String username, String pass) throws UserNotFound,
                                                           FailedToLogin {

    }

    @Override
    public void logout(HttpSession session) {

    }
}
