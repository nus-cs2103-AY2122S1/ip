public class ExitCommand extends Command {

    /**
     * Executes the specific actions for this command.
     *
     * @param tasks Handles the list of tasks.
     * @param ui Handles the user interface.
     * @param storage Handles the saving and loading of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }

    /**
     * Returns true
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
