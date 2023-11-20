package user.Exception;

public class NullUserNameException extends RuntimeException{
    public NullUserNameException() {
        super("User name is NULL");
    }
}
