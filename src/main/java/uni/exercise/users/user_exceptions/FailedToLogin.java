package uni.exercise.users.user_exceptions;

public class FailedToLogin extends Exception {
    FailedToLogin(String message) {
        super(message);
    }
}
