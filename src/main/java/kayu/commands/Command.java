package kayu.commands;

import java.util.List;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.TaskList;
import kayu.storage.Storage;
import kayu.task.Task;

/**
 * Holds the base logic required for other Command classes to utilise.
 */
public abstract class Command {

    protected final String commandParams;

    /**
     * Initializes the Command instance.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public Command(String commandParams) {
        this.commandParams = commandParams;
    }

    /**
     * Initializes the Command instance.
     * Overloads the {@link #commandParams} as an empty String.
     */
    public Command() {
        this.commandParams = "";
    }

    /**
     * Executes the command based on the implemented child instances
     * and returns the outcome as a String.
     *
     * @param taskList {@link kayu.service.TaskList} instance to execute on.
     * @param storage {@link kayu.storage.Storage} instance to save information with.
     * @return String feedback of execution/outcome.
     * @throws KayuException If execution of Command fails.
     * @throws StorageException If saving of information using <code>storage</code> fails.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws KayuException, StorageException;

    /**
     * Returns the command parameters fed.
     *
     * @return String parameters fed into the command by user.
     */
    public String getCommandParams() {
        return commandParams;
    }

    /**
     * Checks if the Command instance is a {@link kayu.commands.ByeCommand}.
     *
     * @return Boolean true if is {@link kayu.commands.ByeCommand}, else false.
     */
    public boolean isBye() {
        return false;
    }

    /**
     * Updates the file storage with the current {@link kayu.service.TaskList}.
     *
     * @param taskList {@link kayu.service.TaskList} instance to execute on.
     * @param storage {@link kayu.storage.Storage} instance to save information with.
     * @throws StorageException If saving of information using <code>storage</code> fails.
     */
    public void updateFileStorage(TaskList taskList, Storage storage) throws StorageException {
        List<Task> tasks = taskList.getTasks();
        storage.saveTasks(tasks);
    }
}
