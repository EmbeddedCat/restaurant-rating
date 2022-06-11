package uni.exercise.users.user_exceptions;

public class UserNotFound extends Exception {
    UserNotFound(String message) {
        super(message);
    }
}
