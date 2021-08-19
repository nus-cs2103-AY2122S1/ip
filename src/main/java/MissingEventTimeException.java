/**
 * Exception to indicate when users enter event without start time
 * e.g. event end of semester party
 */
public class MissingEventTimeException extends DukeException {
    public MissingEventTimeException(String error) {
        super(error);
    }
}
