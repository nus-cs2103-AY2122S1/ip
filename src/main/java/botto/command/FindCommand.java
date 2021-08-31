package botto.command;

import java.util.LinkedList;
import java.util.List;

import botto.BottoException;
import botto.task.Task;
import botto.util.Dialog;
import botto.util.Storage;
import botto.util.TaskList;

public class FindCommand implements Command {
    private String command;

    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Dialog dialog, Storage storage) throws BottoException {
        String keyword;

        try {
            keyword = command.split(" ")[1].toLowerCase();
        } catch (Exception e) {
            throw new BottoException("Please specify the keyword.");
        }

        List<Task> matchesTasks = new LinkedList<>();

        for (Task task: taskList.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchesTasks.add(task);
            }
        }

        dialog.showTasks(matchesTasks, "Here are the matching tasks in your list:");
    }
}
