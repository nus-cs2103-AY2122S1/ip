package duke;

/**
 * Encapsulates a command by the user to exit Duke.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * @param tasks The list of tasks in the to-do-list.
     * @param ui The user interface that deals with interactions with the user.
     * @param storage The storage that Duke uses to deal with loading tasks from and saving tasks to a file.
     * @return A message describing the result of the execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        javafx.application.Platform.exit();
        return "Bye. Hope to see you again soon!";
    }
}
