package dino.command;

import dino.exception.EmptyListException;
import dino.task.TaskList;
import dino.util.Storage;


/**
 * Represents the command for which the user wants to view the list of tasks
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display the task list to the user
     *
     * @param storage the local storage file
     * @param taskList the current task list to be displayed
     * @return the output message after execution
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        return taskList.printTaskList();
    }
}
