package nyx.command;

import java.util.ArrayList;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.Task;
import nyx.task.TaskList;

public class FindCommand extends Command {
    public FindCommand(String information) {
        super(information);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        ArrayList<Task> filteredTasks = taskList.searchTask(information);
        if (filteredTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 1; i <= filteredTasks.size(); i++) {
                output.append(String.format("%d. %s\n", i, filteredTasks.get(i - 1)));
            }
            return output.toString();
        }
    }

    public static void throwEmptyException() throws NyxException {
        throw new NyxException("Please enter the keyword to search tasks by!");
    }
}
