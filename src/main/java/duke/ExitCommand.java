package duke;

/**
 * Represents a command to exit the program.
 * 
 * @author Sherman Ng Wei Sheng
 */
public class ExitCommand extends Command {
    private final boolean isExit;

    /**
     * Constructor for the exit command.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Returns true if the command is a programme terminating command, in this case, this is a terminating command.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command to exit the program.
     * 
     * @param list TaskList before execution of the command.
     * @param ui Ui object to log the execution of the command.
     * @param storage Storage object that references the path to store the updated list of tasks.
     * @throws StorageSavingException If exception encountered when storing the list.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws StorageSavingException {
        String message = "Bye. Hope to see you again soon!";
        ui.printMessage(message);
        ui.closeScanner();
        storage.save(list.convertToStorageString());
    }
}
