package duke.exception;

/**
 * Represents the Incorrect Argument Exception class that is thrown
 * when the user does not enter an integer after 'done' or 'delete.
 */
public class DukeUnableToFind extends DukeIncorrectInputs {
    /**
     * Constructor for the DukeDoneIncorrectArgument Exception.
     */
    public DukeUnableToFind() {
        super("\t⚠️ Sorry! I am unable to find any task with this keyword!\n" +
                        "\tDo note that there should only be ONE keyword after the\n" +
                        "\tcommand 'find'.",
                new IllegalArgumentException());
    }
}
