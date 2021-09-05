package petal.command;

import petal.components.Storage;
import petal.components.TaskList;

/**
 * The ListCommand implements Command and handles
 * displayed the list of tasks to the user
 */
public class ListCommand implements Command {

    /**
     * Prints the current list of tasks
     *
     * @param taskList TaskList instance
     * @param storage Storage instance
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.printCurrTasks();
    }
}
