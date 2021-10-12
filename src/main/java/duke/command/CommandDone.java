package duke.command;

import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;
import duke.task.TaskList;

/**
 * Represents a command to mark a task as completed.
 */
public class CommandDone extends DukeCommand {
    private int taskId;

    /**
     * Creates a new CommandDone.
     *
     * @param taskId ID of the task to be completed.
     */
    public CommandDone(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Sets the task to be complete.
     *
     * @param tl Task list for the user.
     */
    @Override
    public String execute(TaskList tl) {
        return tl.markComplete(taskId);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses the user input into the right format for the command
     *
     * @param userArgs Arguments to the command as provided by the user.
     */
    public static DukeCommand parseCommand(String[] userArgs) throws DukeCommandException, DukeArgumentException {
        assert userArgs != null;
        assert userArgs.length != 0;
        assert userArgs[0].equals("done");

        if (userArgs.length < 2) {
            throw new DukeCommandException("done");
        }

        assert userArgs.length == 2;

        int taskId;
        try {
            taskId = Integer.parseInt(userArgs[1]);
        } catch (NumberFormatException nfe) {
            throw new DukeArgumentException("Incorrect argument for command Delete, must be an integer");
        }

        return new CommandDone(taskId);
    }
}
