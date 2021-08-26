package kermit.command;

import kermit.Storage;
import kermit.ToDo;
import kermit.Ui;

/**
 * List command displays tasks in task list.
 */
public class ListTasksCommand extends Command {

    /**
     * ListTasks command constructor.
     */
    public ListTasksCommand() {}

    /**
     * Executes exit command.
     * List tasks including their short form, completion status and date associated with task
     * @param taskList Instance of task list used.
     * @param ui       Instance of Ui used.
     * @param storage  Instance of storage class used.
     */
    @Override
    public void execute(ToDo taskList, Ui ui, Storage storage) {
        ui.showListItems(taskList);
    }

    /**
     * Returns if command is the exit command.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}