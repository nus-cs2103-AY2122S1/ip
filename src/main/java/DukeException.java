public class DukeException extends Exception {


    /**
     * Constructor for DukeException. DukeException inherits from the Exception parent class.
     * @param errorMessage the error message that is presented when an incorrent input is entered to Duke.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);

    }
}
