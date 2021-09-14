package duke;

/**
 * Represents a command to exit the program.
 *
 * @author Sherman Ng Wei Sheng
 */
public class HelpCommand extends Command {
    private final boolean isExit;

    /**
     * Constructor for the exit command.
     */
    public HelpCommand() {
        this.isExit = false;
    }

    /**
     * Returns true if the command is a programme terminating command, in this case, this is a terminating command.
     *
     * @return True if it is a terminating command and false otherwise.
     */
    @Override
    public boolean isAExitCommand() {
        return isExit;
    }

    /**
     * Executes the command to exit the program.
     *
     * @param list TaskList before execution of the command.
     * @param ui Ui object to log the execution of the command.
     * @param storage Storage object that references the path to store the updated list of tasks.
     * @return The string to be printed.
     * @throws StorageSavingException If exception encountered when storing the list.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws StorageLoadingException {
        String data = HelpProvider.getContent();
        return ui.printAndReturnMessage(data);
    }
}
