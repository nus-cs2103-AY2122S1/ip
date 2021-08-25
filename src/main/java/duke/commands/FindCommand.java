package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        identifyTasksByKeyword(tasks);
    }

    private void identifyTasksByKeyword(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTaskList();
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getName().contains(this.keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasksWithKeyword) {
            task.showThisTask();
        }
    }

    public boolean isExit() {
        return false;
    }
}
