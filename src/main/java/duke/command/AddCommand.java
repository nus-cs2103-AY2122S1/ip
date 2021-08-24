package duke.command;

import java.io.IOException;
import duke.DukeException;
import duke.task.Task;
import duke.storage.TaskList;

/**
 * Represents a command to add new task
 */
public class AddCommand implements Command {
    Task task;

    /**
     * Constructor of AddCommand
     *
     * @param task the task to add
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Runs the command
     *
     * @return message if success
     * @throws DukeException if cannot add new task to memory
     */
    @Override
    public String run() throws DukeException {
        try {
            TaskList.getInstance().add(this.task);
            String resultString =
                    "Got it. I have added this task:\n" +
                    "\t\t" + task.toString() + "\n" +
                    String.format(
                            "\t  Now you have %d task%s in your list.",
                            TaskList.getInstance().getSize(),
                            TaskList.getInstance().getSize() > 1 ? "s" : "");
            return resultString;
        } catch (IOException e) {
            throw new DukeException("Error adding new task!");
        }
    }
}
