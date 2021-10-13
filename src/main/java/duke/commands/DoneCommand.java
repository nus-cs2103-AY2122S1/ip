package duke.commands;

import duke.DukeException;
import duke.Task;
import duke.TaskList;

/**
 * This class encapsulates a Done Command.
 *
 * @author Kleon Ang
 */
public class DoneCommand extends Command {
    public DoneCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String getReply(String arguments) throws DukeException {
        if (arguments.equals("")) {
            throw new DukeException("☹ OOPS!!! The index of 'done' cannot be empty.");
        }
        assert !arguments.equals("") : "Arguments required, cannot be empty.";
        int taskIndex = Integer.parseInt(arguments);
        return done(taskIndex);
    }

    /**
     * Marks the task of the given index as done.
     *
     * @param taskIndex The index of the task to mark as done.
     * @return A string containing a success message for marking the task as done.
     * @throws DukeException A Duke-specific exception that may occur when marking tasks as done.
     */
    private String done(int taskIndex) throws DukeException {
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            throw new DukeException("Sorry, no such task of index " + taskIndex + ".");
        }
        Task doneTask = tasks.get(taskIndex - 1);
        doneTask.markAsDone();
        return "Nice! I've marked this task as done:\n  " + doneTask;
    }
}
