package taskman.exception;

/**
 * Exception that is thrown when filepath does not contain file
 */
public class FileNotFoundException extends DukeException {

    /**
     * Basic Constructor
     *
     * @param errorDetails Exception that is thrown when filepath does not contain file
     */
    public FileNotFoundException(String errorDetails) {
        super(errorDetails);
    }
}
