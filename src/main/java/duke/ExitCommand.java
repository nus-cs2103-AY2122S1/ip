package duke;

/**
 * Encapsulates a command to exit the program.
 */
public class ExitCommand implements Command {

    /**
     * Displays a goodbye message.
     *
     * @param tasks the current list of tasks.
     * @param ui user interface interacts with the user.
     * @param storage custodian of reading and writing save files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayFarewell();
    }

    /**
     * Identifies if this command is an exit command.
     *
     * @return whether this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
