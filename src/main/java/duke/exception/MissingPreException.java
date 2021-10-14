package duke.exception;

/**
 * Thrown when user does not provide correct preposition for certain task descriptions
 */
public class MissingPreException extends DukeException {
    private final String error;

    /**
     * Constructs MissingPreException object
     *
     * @param pre preposition not provided
     */
    public MissingPreException(String pre) {
        super();
        this.error = String.format("OOPS!!! Missing %s preposition!", pre);
    }

    /**
     * Returns error message
     *
     * @return error message
     */
    @Override
    public String getError() {
        return this.error;
    }
}
