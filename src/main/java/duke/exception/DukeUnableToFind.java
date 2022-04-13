package duke.exception;

/**
 * Represents the Incorrect Argument Exception class that is thrown
 * when the user does not enter an integer after 'done' or 'delete.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class DukeUnableToFind extends DukeIncorrectInputs {
    /**
     * Constructor for the DukeDoneIncorrectArgument Exception.
     */
    public DukeUnableToFind() {
        super("⚠️ Sorry! I am unable to find any task with this keyword!\n"
                        + "Do note that there should only be ONE keyword after the command 'find'.",
                new IllegalArgumentException());
    }
}
