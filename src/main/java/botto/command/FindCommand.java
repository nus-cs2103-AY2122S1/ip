package botto.command;

import java.util.LinkedList;
import java.util.List;

import botto.BottoException;
import botto.task.Task;
import botto.util.Dialog;
import botto.util.Storage;
import botto.util.TaskList;

/**
 * Command for finding a keyword.
 */
public class FindCommand implements Command {
    private String command;

    /**
     * Constructor for FindCommand.
     *
     * @param command user command.
     */
    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Print a list of tasks that match the keyword.
     *
     * @param taskList the task list involved.
     * @param dialog the dialog of the Botto bot.
     * @param storage storage of the Botto bot.
     * @throws BottoException when the there is no keyword inserted.
     */
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
            // add the task to the list if it matches the keyword
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchesTasks.add(task);
            }
        }

        dialog.showTasks(matchesTasks, "Here are the matching tasks in your list:");
    }
}
