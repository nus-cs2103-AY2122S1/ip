package duke.response;

/**
 * Represents a bad response after executing a command.
 */
public class DukeBadResponse extends DukeResponse {
    /**
     * Constructor a <code>DukeBadResponse</code> object.
     */
    public DukeBadResponse(String message) {
        super(message);
    }
}
