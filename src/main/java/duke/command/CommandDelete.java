package duke.command;

import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;
import duke.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class CommandDelete extends DukeCommand {
    private int taskId;

    /**
     * Creates a new CommandDelete.
     *
     * @param taskId ID of the task to be deleted.
     */
    public CommandDelete(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Removes the specified task from the task list.
     *
     * @param tl Task list for the user.
     */
    @Override
    public String execute(TaskList tl) {
        return tl.deleteTask(taskId);
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
        assert userArgs[0].equals("delete");

        if (userArgs.length < 2) {
            throw new DukeCommandException("delete");
        }

        assert userArgs.length == 2;

        int taskId;
        try {
            taskId = Integer.parseInt(userArgs[1]);
        } catch (NumberFormatException nfe) {
            throw new DukeArgumentException("Incorrect argument for command Delete, must be an integer");
        }

        return new CommandDelete(taskId);
    }
}
