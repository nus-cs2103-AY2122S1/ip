package main.java;

/**
 * Bye is a command which closes the Duke bot and its readers.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class Bye extends Command {

    /**
     * Constructor.
     *
     * @param description empty string
     */
    Bye(String description) {
        super(description);
    }

    /**
     * Closes the Ui and all its readers.
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage for the saved task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.close();
    }

    /**
     * Returns true if this command is executed.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
