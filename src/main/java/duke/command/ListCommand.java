package duke.command;

import java.util.ArrayList;
import duke.DukeException;
import duke.storage.TaskList;
import duke.task.Task;

public class ListCommand implements Command{
    @Override
    public String run() throws DukeException {
        if (TaskList.getInstance().getSize() == 0) {
            return "No task added yet";
        }
        ArrayList<Task> tasks = TaskList.getInstance().getAll();
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message += String.format("\t\t%d. %s\n", i + 1, tasks.get(i).toString());
        }
        return message;
    }
}
