package ponyo.commands;

import ponyo.data.task.Task;
import ponyo.data.task.TaskList;
import ponyo.ui.Ui;
import ponyo.storage.Storage;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keywordToFind;

    public FindCommand(String keywordToFind) {
        this.keywordToFind = keywordToFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskClone = new ArrayList<>();

        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.retrieveTask(i).containsKeyword(keywordToFind)) {
                taskClone.add(tasks.retrieveTask(i));
            }
        }

        if (taskClone.size() != 0) {
            ui.show("\tHere are the matching tasks in your list: ");
            for (int i = 1; i <= taskClone.size(); i++) {
                ui.show("\t" + i + "." + taskClone.get(i - 1));
            }
        } else {
            ui.show("There are no matching tasks in your list.");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
