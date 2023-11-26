package submit.exception;

public class ConferenceNotFoundException extends RuntimeException {

    public ConferenceNotFoundException(String name) {
        super("Could not find Conference " + name);
    }
}
