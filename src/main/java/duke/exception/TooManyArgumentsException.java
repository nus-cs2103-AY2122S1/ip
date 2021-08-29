package duke.exception;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class that encapsulates a too many arguments exception that might be
 * raised whilst interacting with chatbot, Duke.
 *
 * @author Keith Tan
 */
public class TooManyArgumentsException extends DukeException {

    /**
     * Constructor for TooManyArgumentsException
     * Exception when user inputs too many arguments for the commands
     */
    public TooManyArgumentsException(String missingCondition, String origin) {

        super("Hi, there are too many " + missingCondition + " for the " + origin + "!");

    }

}
