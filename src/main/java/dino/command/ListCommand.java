package dino.command;

import dino.exception.EmptyListException;
import dino.util.Storage;
import dino.task.TaskList;

/**
 * Represents the command for which the user wants to view the list of tasks
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display the task list to the user
     *
     * @param storage the local storage file
     * @param taskList the current task list to be displayed
     * @throws EmptyListException if the list to be displayed is empty
     */
    @Override
    public void execute(Storage storage, TaskList taskList) throws EmptyListException {
        taskList.printTaskList();
    }
}
