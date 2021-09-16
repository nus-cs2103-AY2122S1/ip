package command;

import java.util.ArrayList;

import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * ListCommand will list out the task number, task description and date(if applicable) when executed.
 */
public class ListCommand extends Command {

    /**
     * Executes the given command returned by parse method.
     * Each command class will have its own interaction with Ui, TaskList and Storage
     *
     * @param tasks the TaskList loaded from storage.
     * @param storage accesses the file location in local storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        String returnedString = "Here are your tasks: \n";
        for (int i = 0; i < taskList.size(); i++) {
            returnedString += String.format("%d.%s\n", (i + 1), taskList.get(i));
        }
        return returnedString;
    }
}
