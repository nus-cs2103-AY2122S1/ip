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
    public void execute(TaskList tasks, Storage storage) {
        ArrayList<Task> taskClone = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.retrieveTask(i).containsKeyword(keywordToFind)) {
                taskClone.add(tasks.retrieveTask(i));
            }
        }

        if (taskClone.size() != 0) {
            Ui.show("\tHere are the matching tasks in your list: ");
            for (int i = 0; i < taskClone.size(); i++) {
                Ui.show("\t" + (i + 1) + "." + taskClone.get(i));
            }
        } else {
            Ui.show("There are no matching tasks in your list.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
