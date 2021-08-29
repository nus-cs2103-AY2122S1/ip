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
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
