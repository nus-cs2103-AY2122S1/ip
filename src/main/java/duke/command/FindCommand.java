package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Command that finds the tasks that matches a keyword.
 */
public class FindCommand extends Command {
    private final String text;

    /**
     * Returns a new FindCommand object.
     * @param text The keyword.
     */
    public FindCommand(String text) {
        this.text = text;
    }

    /**
     * Executes the command to find the tasks.
     * @param tasks The list of tasks.
     * @param ui The Ui object.
     * @param storage The Storage object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.toString().contains(text)) {
                matchingTasks.add(task);
            }
        }
        TaskList mTasks = new TaskList(matchingTasks);
        ui.showMatchingTasks(mTasks);
    }

    /**
     * If the command is the exit command.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
