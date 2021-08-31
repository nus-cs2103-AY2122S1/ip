package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_NOT_AN_INT_PARAM;
import static kayu.commands.CommandMessage.MESSAGE_TASK_DONE;
import static kayu.commands.CommandType.DONE;

import kayu.exception.DukeException;
import kayu.exception.StorageException;
import kayu.service.TaskList;
import kayu.storage.Storage;
import kayu.task.Task;

/**
 * Represents a {@link kayu.commands.Command} that marks a certain {@link kayu.task.Task}
 * to be marked as done in {@link kayu.service.TaskList}.
 */
public class DoneCommand extends Command {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "done";

    /**
     * Initializes a Done- {@link kayu.commands.Command}.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public DoneCommand(String commandParams) {
        super(DONE, commandParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException, StorageException {
        try {
            int taskNumber = Integer.parseInt(commandParams);
            Task selectedTask = taskList.updateTaskAsDone(taskNumber);
            super.saveTasks(taskList, storage);
            return String.format(MESSAGE_TASK_DONE, selectedTask);

        } catch (NumberFormatException exception) {
            throw new DukeException(String.format(ERROR_NOT_AN_INT_PARAM, commandParams));
        }
    }
}
