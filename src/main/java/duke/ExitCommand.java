package duke;

/**
 * Encapsulates a command by the user to exit the program and close Duke.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * @param tasks The list of tasks in the to-do-list.
     * @param ui The user interface that deals with interactions with the user.
     * @param storage The storage that Duke uses to deal with loading tasks from and saving tasks to a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return True if the command is an exit command, False otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
