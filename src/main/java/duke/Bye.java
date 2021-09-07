package duke;

/**
 * Runs the bye command.
 */
public class Bye extends Command {

    public Bye() {

    }

    /**
     * Runs the bye command.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui to display to the user.
     * @param storage Storage to store tasks.
     * @return String to be displayed.
     */
    public String run(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }
}
