package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * A command class encapsulating the logic that occurs when the user issues a 'list' command.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    /**
     * Causes the Ui to print the task list
     *
     * @param tasks List of existing tasks
     * @param ui User interface current interacting with the user
     * @param storage Storage class handling the persistence of the tasks
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showList(tasks);
    }

    /**
     * Indicate if another object is 'equal' to this object.
     *
     * @param obj Reference object with which to compare to.
     * @return true if they are equal.
     *         false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
