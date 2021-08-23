/**
 * Contains the executables when the user uses the 'bye' command.
 */
public class ByeCommand extends Command {
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
        storage.save(taskList);
    }

    /**
     * Overrides the isExit function to tell Duke to exit.
     */
    @Override
    boolean isExit() {
        return true;
    }
}
