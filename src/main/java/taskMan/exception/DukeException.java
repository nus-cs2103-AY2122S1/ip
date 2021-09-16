package taskMan.exception;

/**
 * Exception that is thrown when user inputs something invalid in general
 */
public class DukeException extends Exception {
    private String errorDetails;
    /**
     * Basic Constructor
     * @param errorDetails Explanation as to why error occurred
     */
    public DukeException(String errorDetails){
        super();
        this.errorDetails = errorDetails;
    }

    /**
     * Overrides the toString() of DukeException so that it would only pass the error details
     *
     * @return
     */
    @Override
    public String toString() {
        return this.errorDetails + " Please try again";
    }
}
