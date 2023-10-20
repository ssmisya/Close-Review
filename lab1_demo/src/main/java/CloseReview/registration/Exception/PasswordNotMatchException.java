package CloseReview.registration.Exception;

public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException(){
        super("Two passwords not match");
    }
}
