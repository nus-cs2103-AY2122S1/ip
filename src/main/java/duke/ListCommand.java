package duke;

/**
 * Encapsulates a command by the user to list the current items in the to-do-list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param tasks The list of tasks in the to-do-list.
     * @param ui The user interface that deals with interactions with the user.
     * @param storage The storage that Duke uses to deal with loading tasks from and saving tasks to a file.
     * @return A message describing the result of the execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.toString();
    }
}
