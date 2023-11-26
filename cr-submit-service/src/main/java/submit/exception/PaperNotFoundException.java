package submit.exception;

public class PaperNotFoundException extends RuntimeException {

    public PaperNotFoundException(String name) {
        super("Could not find Paper using " + name);
    }
}
