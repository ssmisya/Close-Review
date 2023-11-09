package CloseReview.registration.Exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String user_name) {
        super("User" + user_name + "already exists");
    }
}
