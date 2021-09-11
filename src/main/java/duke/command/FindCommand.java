package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.gui.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.toString().contains(text)) {
                matchingTasks.add(task);
            }
        }
        TaskList mTasks = new TaskList(matchingTasks);
        return ui.showMatchingTasks(mTasks);
    }

    /**
     * If the command is the exit command.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
