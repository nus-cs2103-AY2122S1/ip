package duke.exception;

/**
 * Exception that is thrown when filepath does not contain file
 */
public class FileNotFoundException extends DukeException {

    /**
     * Basic Constructor
     *
     * @param msg Explanation as to why error occured
     */
    public FileNotFoundException(String msg) {
        super(msg);
    }
}
