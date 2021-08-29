package duke.exception;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class that encapsulates a missing argument exception that might be
 * raised whilst interacting with chatbot, Duke.
 *
 * @author Keith Tan
 */
public class MissingArgumentException extends DukeException {

    /**
     * Constructor for MissingArgumentException
     * Exception when user is missing any arguments for the commands
     */
    public MissingArgumentException(String missingCondition, String origin) {

        super("Hi, you are missing the " + missingCondition + " for the " + origin + "!");

    }

}
