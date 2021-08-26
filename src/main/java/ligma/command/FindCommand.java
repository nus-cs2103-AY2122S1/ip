package ligma.command;

import ligma.Storage;
import ligma.TaskList;
import ligma.Ui;
import ligma.task.Task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a command to find
 * tasks containing a specific target string.
 */
public class FindCommand implements Command {
    private String target;

    public FindCommand(String target) {
        this.target = target;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        Task[] results = tasks.find(target);
        Ui.printFoundTasks(results);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
