package command;

import java.util.ArrayList;
import java.util.Locale;

import storage.Storage;
import task.Task;
import task.TaskList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the given command returned by parse method.
     * Each command class will have its own interaction with Ui, TaskList and Storage
     *
     * @param tasks the TaskList loaded from storage.
     * @param storage accesses the file location in local storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String returnedString = "Here are the matching tasks in your list:\n";
        ArrayList<Task> taskList = tasks.getTaskList();
        int index = 1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT))) {
                returnedString += String.format("%d.%s\n", index, taskList.get(i));
                index++;
            }
        }
        return returnedString;
    }
}
