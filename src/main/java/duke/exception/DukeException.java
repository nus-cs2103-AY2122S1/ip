package duke.exception;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class to encapsulates all possible exceptions that will be
 * raised whilst interacting with chatbot, Duke.
 *
 * @author Keith Tan
 */
public class DukeException extends Exception {

    private String message;

    /**
     * Constructor for DukeException.
     * Exception that might be raised during user interaction with Duke
     */
    public DukeException(String message) {

        this.message = message;

    }

    @Override
    public String toString() {

        return this.message;

    }

}
