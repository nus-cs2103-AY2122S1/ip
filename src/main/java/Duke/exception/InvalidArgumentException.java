package duke.exception;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: A-CodingStandard. Modify the code to comply with a given coding standard
 *
 * Description:
 * Class that encapsulates an invalid argument exception that might be
 * raised whilst interacting with chatbot, Duke.
 *
 * @author Keith Tan
 */
public class InvalidArgumentException extends DukeException {

    public InvalidArgumentException(String invalidCondition, String origin) {

        super("Hi, the " + invalidCondition + " for the " + origin + " is invalid!");

    }

}
