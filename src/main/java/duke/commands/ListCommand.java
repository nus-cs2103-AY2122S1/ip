package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
/**
 * Represents a ListCommand to display all the tasks in a TaskList to a user.
 */
public class ListCommand extends Command {
    public ListCommand() {
    }

    /**
     * Prints the tasks in the given TaskList.
     * 
     * @param tasks TaskList containing tasks to be printed.
     * @param ui Ui to show messages to user when ListCommand is executed.
     * @param storage Storage where tasks should be stored.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTaskList();
    }

    /**
     * Returns true if object is a ListCommand, else false.
     *
     * @param obj Object to be compared to ListCommand.
     * @return True if obj is a ListCommand, else false.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
