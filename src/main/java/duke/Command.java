package duke;

/**
 * Encapsulates a command entered by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks in the to-do-list.
     * @param ui The user interface that deals with interactions with the user.
     * @param storage The storage that Duke uses to deal with loading tasks from and saving tasks to a file.
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates whether the command is an exit command.
     *
     * @return True if the command is an exit command, False otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
