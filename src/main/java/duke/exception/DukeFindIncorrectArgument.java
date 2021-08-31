package duke.exception;

/**
 * Represents the Incorrect Argument Exception class that is thrown
 * when the user does not enter an integer after 'done' or 'delete.
 */
public class DukeFindIncorrectArgument extends DukeIncorrectInputs {
    /**
     * Constructor for the DukeDoneIncorrectArgument Exception.
     */
    public DukeFindIncorrectArgument() {
        super("⚠️ After the keyword, you have to enter ONE keyword.",
                new IllegalArgumentException());
    }
}