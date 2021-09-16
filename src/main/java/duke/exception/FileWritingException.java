package duke.exception;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class that encapsulates a file writing exception that might be
 * raised whilst interacting with chatbot, Duke.
 *
 * @author Keith Tan
 */
public class FileWritingException extends DukeException {

    /**
     * Constructor for FileWritingException
     * Exception raised when saving the tasklist to the hard drive
     */
    public FileWritingException() {

        super("Hi, Duke ran into an error trying to save task to your hard drive. Please try again!");

    }

}
