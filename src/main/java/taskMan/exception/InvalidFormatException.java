package taskMan.exception;

/**
 * Exception to display error made by using in regards to format in general
 */
public class InvalidFormatException extends DukeException {

    /**
     * Basic Constructor
     *
     * @param errorDetails Exception to display error made by using in regards to format in general
     */
    public InvalidFormatException(String errorDetails){
        super(errorDetails);
    }
}
