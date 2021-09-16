package duke.exception;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class that encapsulates an invalid command exception that might be
 * raised whilst interacting with chatbot, Duke.
 *
 * @author Keith Tan
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructor for InvalidCommandException
     * Exception when user inputs a invalid coomand
     */
    public InvalidCommandException() {

        super("Hi I don't understand your command :(");

    }

}
