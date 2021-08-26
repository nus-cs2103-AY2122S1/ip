package duke;

/**
 * Represents the command to print the list.
 */
public class PrintCommand extends Command {
    
    private boolean isExit;

    /**
     * Constructor for the print command.
     */
    public PrintCommand() {
        this.isExit = false;
    }

    /**
     * Returns true if the command is a programme terminating command.
     *
     * @return True if it is a terminating command and false otherwise.
     */
    @Override
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command to print the list.
     * 
     * @param list TaskList before execution of the command.
     * @param ui Ui object to log the execution of the command.
     * @param storage Storage object that references the path to store the updated list of tasks.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.printList(list);
    }
}
