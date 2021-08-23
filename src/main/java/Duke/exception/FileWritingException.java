package duke.exception;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: A-CodingStandard. Modify the code to comply with a given coding standard
 *
 * Description:
 * Class that encapsulates a file writing exception that might be
 * raised whilst interacting with chatbot, Duke.
 *
 * @author Keith Tan
 */
public class FileWritingException extends DukeException {

    public FileWritingException() {

        super("Hi, Duke.util.Duke ran into an error trying to save task to your hard drive. Please try again!");

    }

}
