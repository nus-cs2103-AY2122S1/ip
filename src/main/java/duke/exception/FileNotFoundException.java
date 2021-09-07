package duke.exception;

/**
 * Exception that is thrown when filepath does not contain file
 */
public class FileNotFoundException extends DukeException {

    /**
     * Basic Constructor
     *
     * @param errorDetails Explanation as to why error occured
     */
    public FileNotFoundException(String errorDetails) {
        super(errorDetails);
    }
}
