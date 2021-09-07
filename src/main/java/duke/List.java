package duke;

/**
 * Represents the list command.
 */
public class List extends Command {

    public List(){

    }

    /**
     * Runs the list command.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui to display to the user.
     * @param storage Storage to store tasks.
     * @return String to be displayed.
     */
    public String run(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
