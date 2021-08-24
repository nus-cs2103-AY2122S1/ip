package duke.command;

import java.io.IOException;
import duke.DukeException;
import duke.task.Task;
import duke.storage.TaskList;

public class DeleteCommand implements Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String run() throws DukeException {
        try {
            Task deleted = TaskList.getInstance().delete(index);
            String message = "Okay, I have removed this task:\n" +
                    "\t\t" + deleted.toString() + "\n" +
                    String.format("\t  Now you have %d task%s in your list.", TaskList.getInstance().getSize(), TaskList.getInstance().getSize() > 1 ? "s" : "");
            return message;
        } catch (IOException e) {
            throw new DukeException("Error removing task!");
        }
    }
}
