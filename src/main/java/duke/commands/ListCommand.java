package duke.commands;

import duke.exceptions.EmptyListException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This is a ListCommand class that extends Command.
 */
public class ListCommand extends Command {

    /**
     * This is a ListCommand Constructor.
     */
    public ListCommand() {
        super("list");
    }

    @Override
    public String execute(TaskList taskList, Storage store, Ui ui) throws EmptyListException {
        return taskList.printTasks(ui);
    }

    @Override
    public String toString() {
        return this.command;
    }
}
