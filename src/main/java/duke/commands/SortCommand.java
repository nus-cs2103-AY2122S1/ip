package duke.commands;

import duke.exceptions.DukeFileException;
import duke.exceptions.EmptyListException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This is the sort command class that call for sorting of all tasks in the list.
 */
public class SortCommand extends Command {

    /**
     * This is the constructor for SortCommand.
     */
    public SortCommand() {
        super("sort");
    }

    @Override
    public String execute(TaskList taskList, Storage store, Ui ui) throws DukeFileException, EmptyListException {
        if (taskList.getSize() == 0) {
            throw new EmptyListException();
        }
        taskList.sortList();
        taskList.safeTasks(store);
        return taskList.printTasks(ui);
    }
}
