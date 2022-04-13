package main.java.duke.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.duke.MainWindow;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.tasks.Task;

/**
 * A command that looks for tasks with keyword given from the task list.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(TaskList tasks, MainWindow gui, Storage storage) {
        return identifyTasksByKeyword(tasks);
    }

    private String identifyTasksByKeyword(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTaskList();
        Stream<Task> taskStream = taskList.stream();
        taskStream = taskStream.filter(task -> task.getName().contains(this.keyword));
        List<Task> tasksWithKeyword = taskStream.collect(Collectors.toList());
        StringBuilder message = new StringBuilder(("Here are the matching tasks in your list: \n"));
        for (Task task : tasksWithKeyword) {
            message.append(task.toString());
        }
        return message.toString();
    }
}
