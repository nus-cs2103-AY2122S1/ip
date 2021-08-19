/**
 * Represents the Incorrect Argument Exception class that is thrown
 * when the user does not enter an integer after 'done'.
 */
public class DukeDoneIncorrectArgument extends DukeIncorrectInputs {
    /**
     * Constructor for the DukeDoneIncorrectArgument Exception.
     */
    public DukeDoneIncorrectArgument() {
        super("\t⚠️ After the keyword, you have to enter an integer.",
                new IllegalArgumentException());
    }
}
