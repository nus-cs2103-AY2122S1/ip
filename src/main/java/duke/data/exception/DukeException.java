package duke.data.exception;

/**
 * Class that notifies an error that Duke encounters.
 *
 * @author Wang Hong Yong
 */
public class DukeException extends RuntimeException {

    /**
     * Constructor for DukeException.
     *
     * @param errorMessage should contain relevant information on the failed execution(s).
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
