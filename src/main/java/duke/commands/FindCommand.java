package main.java.duke.commands;

import java.util.ArrayList;

import main.java.duke.MainWindow;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.tasks.Task;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(TaskList tasks, MainWindow gui, Storage storage) {
        return identifyTasksByKeyword(tasks);
    }

    private String identifyTasksByKeyword(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTaskList();
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getName().contains(this.keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        String message1 = ("Here are the matching tasks in your list: \n");
        String message2 = "";
        for (Task task : tasksWithKeyword) {
            message2 += task.toString();
        }
        return message1 + message2;
    }

    public boolean isExit() {
        return false;
    }
}
