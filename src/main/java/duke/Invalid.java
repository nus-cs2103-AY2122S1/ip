package duke;

/**
 * Represents an invalid command.
 */
public class Invalid extends Command {

    public Invalid() {

    }

    /**
     * Shows exception for an invalid command.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui to display to the user.
     * @param storage Storage to store tasks.
     * @return String to be displayed.
     */
    public String run(TaskList tasks, Ui ui, Storage storage) {
        return ui.showException(new InvalidInputException("sorry invalid input!!!!"));
    }
}
