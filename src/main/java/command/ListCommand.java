package command;

import java.util.ArrayList;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * ListCommand will list out the task number, task description and date(if applicable) when executed.
 */
public class ListCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        String returnedString = "Here are your tasks: \n";
        for (int i = 0; i < taskList.size(); i++) {
            returnedString += String.format("%d.%s\n", (i + 1), taskList.get(i));
        }
        return returnedString;
    }
}
