package duke.commands;

import duke.exceptions.EmptyListException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * This is a ListCommand class that extends Command.
 */
public class ListCommand extends Command {

    /**
     * This is a ListCommand Constructor
     */
    public ListCommand() {
        super("list");
    }

    @Override
    public void execute(TaskList taskList, Storage store, Ui ui) throws EmptyListException {
        taskList.printTasks(ui);
    }

    @Override
    public String toString() {
        return this.command;
    }
}
