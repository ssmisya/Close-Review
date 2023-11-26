package conference.exception;

public class ConferenceNotFoundException extends RuntimeException {

    public ConferenceNotFoundException(String id) {
        super("Could not find Conference " + id);
    }
}
