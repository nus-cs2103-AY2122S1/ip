package duke.command;

import duke.exception.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Utils;
import duke.exception.TaskNotFoundException;
import duke.task.Task;

/** Command for marking a task as done */
public class DoneCommand extends Command {

    /**
     * Constructor for a DoneCommand
     * @param userCommand The command the user gives
     * @param userArgument The argument (rest of the String after the command)
     */
    public DoneCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    /**
     * Marks a Task as done
     * @return A String of the task that was marked as done
     * @throws DukeException If the specified task cannot be found
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {

        assert userArgument != null;

        int done = Utils.getInputNumber(userArgument);

        if (done >= tasks.numberOfTasks() || done < 0) {
            throw new TaskNotFoundException();
        }

        Task doneTask = tasks.getTask(done);
        doneTask.markAsDone();
        storage.editTaskFromFile(done, tasks);

        return String.format("I've marked this task as done:\n"
                + "%s\n", doneTask.toString());
    }

}
