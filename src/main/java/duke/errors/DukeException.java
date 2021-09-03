package duke.errors;

/** The abstract class to be extended by the DukeException subclasses. */
public abstract class DukeException extends Exception {

    /** The error code. */
    private final int errorCode;

    /**
     * The constructor for the DukeException.
     *
     * @param errorCode The error code
     */
    protected DukeException (int errorCode) {
        this.errorCode = errorCode;
    }

    public abstract String getErrorDescription();

    /**
     * Gets the error message of the DukeError object.
     *
     * @return The error code
     */
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        return "DukeError " + this.getErrorCode() + ": " + this.getErrorDescription();
    }
}
