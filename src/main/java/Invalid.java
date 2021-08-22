package main.java;

/**
 * Invalid is a command that is invalid.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class Invalid extends Command {

    /**
     * Constructor.
     *
     * @param description it should not contain any description.
     */
    Invalid(String description) {
        super(description);
    }

    /**
     * Simply throws an exception.
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage for the saved task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        throw new DukeException("Invalid command.");
    }
}
