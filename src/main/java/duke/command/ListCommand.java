package duke.command;

import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

/**
 * This class encapsulates the list command.
 *
 * @author Teo Sin Yee
 */
public class ListCommand extends Command {
    /**
     * Prints the task list.
     *
     * @param taskHandler TaskHandler of Duke.
     * @param ui User interface.
     * @param storage Storage for Duke.
     */
    @Override
    public void execute(TaskHandler taskHandler, Storage storage, Ui ui) {
        taskHandler.printTasks();
    }

    /**
     * Indicates whether some other object is equal to this one.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}