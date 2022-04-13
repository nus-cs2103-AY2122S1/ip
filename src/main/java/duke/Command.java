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
     * @return A message describing the result of the execution.
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
