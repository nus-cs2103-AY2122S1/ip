package ponyo.commands;

import java.util.ArrayList;

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
        ArrayList<Task> taskClone = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.retrieveTask(i).containsKeyword(keywordToFind)) {
                taskClone.add(tasks.retrieveTask(i));
            }
        }

        if (taskClone.size() != 0) {
            String[] args = new String[taskClone.size() + 1];
            args[0] = "Here are the matching tasks in your list: ";

            for (int i = 0; i < taskClone.size(); i++) {
                args[i + 1] = (i + 1) + ". " + taskClone.get(i);
            }
            return Ui.show(args);
        } else {
            return Ui.show("There are no matching tasks in your list.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
