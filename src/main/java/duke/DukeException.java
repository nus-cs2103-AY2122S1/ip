package duke;

public class DukeException extends Exception {


    /**
     * Constructor for duke.DukeException. duke.DukeException inherits from the Exception parent class.
     * @param errorMessage the error message that is presented when an incorrent input is entered to duke.Duke.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);

    }
}
