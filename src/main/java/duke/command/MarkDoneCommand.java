package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.storage.TaskList;

/**
 * Command to mark a task as done
 */
public class MarkDoneCommand implements Command {
    private int index;

    /**
     * Constructor of <code>MarkDoneCommand</code>
     *
     * @param index index of the task
     */
    public MarkDoneCommand(int index) {
        this.index = index;
    }

    /**
     * Runs the command
     *
     * @return message if success
     * @throws DukeException if cannot access the memory
     */
    @Override
    public String run() throws DukeException {
        try {
            TaskList.getInstance().markDone(index);
        } catch (IOException e) {
            throw new DukeException("Error marking task as done");
        }
        String resultString =
                "Nice, I have marked this task as done:\n" +
                TaskList.getInstance().get(index).toString();
        return resultString;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
