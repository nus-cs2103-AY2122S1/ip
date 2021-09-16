package duke.exception;

/**
 * InvalidEventDukeException class.
 * Used to represent a invalid event command.
 */
public class InvalidEventDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "\"OOPS!!! Parameter /at is missing.\n"
            + "eg. event Meeting /at 31/12/2021 1800\n"
            + "    event Meeting /at Friday\n";

    /**
     * InvalidEventDukeException constructor.
     */
    public InvalidEventDukeException() {
        super(ERROR_MESSAGE);
    }
}
