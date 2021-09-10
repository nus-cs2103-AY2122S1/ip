package duke.command;

import duke.Duke;
import duke.Storage;
import duke.exception.DukeException;
import duke.exception.DukeIllegalFormatException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * TodoCommand class encapsulates command to add a new todo.
 */
public class TodoCommand extends AddCommand {
    /**
     * Constructs a TodoCommand with the specified command.
     *
     * @param command Body of the command.
     */
    public TodoCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Duke duke, TaskList tasks, Storage storage) throws DukeException {
        String[] line = command.split(" /tag ");
        if (line.length < 1) {
            throw new DukeIllegalFormatException(
                "☹ OOPS!!! Seems like you have entered a wrong format for an event task. "
                    + "Try this instead: event <command> /at <date>"
            );
        }
        Task task;
        String[] tags = line.length > 1 ? line[1].split(" ") : new String[0];
        task = new Todo(line[0], tags);
        tasks.add(task, storage);
        String message = "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.toArray().length
            + " task(s) in the list.";
        duke.setResponse(message);
    }
}
