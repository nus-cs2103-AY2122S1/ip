package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a Command to print the list of tasks.
 */

public class ListCommand extends Command {

    /**
     * Prints the lists of tasks
     *
     * @param tasks TaskList to print
     * @param ui UI to print out the TaskList
     * @param storage unused.
     */

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }

}