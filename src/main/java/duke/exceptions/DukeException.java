package duke.exceptions;

public class DukeException extends Exception {
    /**
     * Class Constructor
     * @param errorMessage ErrorMessage to be displayed
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
