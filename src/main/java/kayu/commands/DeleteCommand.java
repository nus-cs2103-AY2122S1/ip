package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_NOT_AN_INT_PARAM;
import static kayu.commands.CommandMessage.MESSAGE_DELETED_TASK;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;
import kayu.task.Task;

/**
 * Represents a {@link kayu.commands.Command} that deletes a certain {@link kayu.task.Task}
 * in {@link TaskList}.
 */
public class DeleteCommand extends Command {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "delete";

    /**
     * Initializes a Delete- {@link kayu.commands.Command}.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public DeleteCommand(String commandParams) {
        super(commandParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList,
                          TaskStorage taskStorage,
                          NoteList noteList,
                          NoteStorage noteStorage)
            throws KayuException, StorageException {

        try {
            int taskNumber = Integer.parseInt(commandParams);
            Task selectedTask = taskList.deleteTask(taskNumber);
            super.updateTaskFileStorage(taskList, taskStorage);
            return String.format(MESSAGE_DELETED_TASK, selectedTask, taskList.getCurrentCapacity());

        } catch (NumberFormatException exception) {
            throw new KayuException(String.format(ERROR_NOT_AN_INT_PARAM, commandParams));
        }
    }
}
