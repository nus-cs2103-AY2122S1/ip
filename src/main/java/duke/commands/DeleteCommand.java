package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * This class encapsulates a Delete Command.
 *
 * @author Kleon Ang
 */
public class DeleteCommand extends Command {
    public DeleteCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String getReply(String arguments) throws DukeException {
        if (arguments.equals("")) {
            throw new DukeException("☹ OOPS!!! The index of 'delete' cannot be empty.");
        }
        assert !arguments.equals("") : "Arguments required, cannot be empty.";
        int counter = Integer.parseInt(arguments);
        return tasks.deleteTask(counter);
    }
}
