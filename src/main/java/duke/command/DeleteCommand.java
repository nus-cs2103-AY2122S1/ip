package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.storage.TaskList;
import duke.task.Task;

/**
 * Represents a command to delete a task
 */
public class DeleteCommand implements Command {
    private int index;

    /**
     * Constructor for <code>DeleteCommand</code>
     *
     * @param index index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Runs the command
     *
     * @return message if success
     * @throws DukeException if cannot delete task from memory
     */
    @Override
    public String run() throws DukeException {
        try {
            Task deleted = TaskList.getInstance().delete(index);
            String message = "Okay, I have removed this task:\n" +
                    "\t\t" + deleted.toString() + "\n" +
                    String.format(
                            "\t  Now you have %d task%s in your list.",
                            TaskList.getInstance().getSize(),
                            TaskList.getInstance().getSize() > 1 ? "s" : "");
            return message;
        } catch (IOException e) {
            throw new DukeException("Error deleting task!");
        }
    }
}
