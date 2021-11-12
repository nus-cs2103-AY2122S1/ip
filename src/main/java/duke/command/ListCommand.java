package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printList(tasks);
    }

}