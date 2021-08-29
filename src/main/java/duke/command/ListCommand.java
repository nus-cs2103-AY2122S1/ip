package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.storage.TaskList;
import duke.task.Task;

/**
 * Command to list all tasks
 */
public class ListCommand implements Command {
    /**
     * Runs the command
     *
     * @return message if success
     * @throws DukeException if cannot read tasks from memory
     */
    @Override
    public String run() throws DukeException {
        if (TaskList.getInstance().getSize() == 0) {
            return "No task added yet";
        }
        ArrayList<Task> tasks = TaskList.getInstance().getAll();
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message += String.format("%d. %s\n", i + 1, tasks.get(i).toString());
        }
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
