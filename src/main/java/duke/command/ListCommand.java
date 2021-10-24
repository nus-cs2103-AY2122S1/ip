package duke.command;

import java.util.ArrayList;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {

    public ListCommand(String input) throws DukeException {
        if (!input.equals("list")) {
            throw new DukeException("Invalid command entered. Did you mean \"list\"?");
        }
    }

    /**
     * Returns the result of the execution of the list command.
     * @param tasks List of tasks the user has added.
     * @return Result of the execution of the list command.
     */
    @Override
    public String execute(TaskList tasks) {
        String reply = "Here are the tasks in your list:\n";
        ArrayList<Task> taskList = tasks.getTasks();
        int count = 0;
        for (Task task : taskList) {
            count++;
            reply += "  " + count + "." + task.toString() + "\n";
        }
        return reply;
    }
}
