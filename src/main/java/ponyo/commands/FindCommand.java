package ponyo.commands;

import java.util.ArrayList;

import ponyo.common.Messages;
import ponyo.data.task.Task;
import ponyo.data.task.TaskList;
import ponyo.storage.Storage;
import ponyo.ui.Ui;

/**
 * Search for a task using a keyword.
 */
public class FindCommand extends Command {
    private final String keywordToFind;

    public FindCommand(String keywordToFind) {
        this.keywordToFind = keywordToFind;
    }

    @Override
    public String[] execute(TaskList tasks, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.retrieveTask(i).containsKeyword(keywordToFind)) {
                matchingTasks.add(tasks.retrieveTask(i));
            }
        }

        if (matchingTasks.isEmpty()) {
            return Ui.show(Messages.MESSAGE_NO_MATCHING_TASKS);
        }

        return getMatchingTasks(matchingTasks);
    }

    /**
     * Gets all matching task to keyword.
     *
     * @param matchingTasks a list of Task objects
     * @return string representation of all matching tasks to print
     */
    private String[] getMatchingTasks(ArrayList<Task> matchingTasks) {
        assert matchingTasks.size() > 0;
        String[] args = new String[matchingTasks.size() + 1];
        args[0] = "Here are the matching tasks in your list: ";

        for (int i = 0; i < matchingTasks.size(); i++) {
            args[i + 1] = (i + 1) + ". " + matchingTasks.get(i);
        }
        return Ui.show(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
