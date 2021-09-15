package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;
import java.util.Locale;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String returnedString = "Here are the matching tasks in your list:";
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
