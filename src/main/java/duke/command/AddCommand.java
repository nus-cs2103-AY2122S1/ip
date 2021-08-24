package duke.command;

import java.io.IOException;
import duke.DukeException;
import duke.task.Task;
import duke.storage.TaskList;

public class AddCommand implements Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

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
