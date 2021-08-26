package duke;

/**
 * Represents a command to delete a task.
 * 
 * @author Sherman Ng Wei Sheng
 */
public class DeleteCommand extends Command {
    
    private boolean isExit;
    private int index;

    /**
     * Constructor for the delete command.
     * 
     * @param index The index of the task in the TaskList to be deleted.
     */
    public DeleteCommand(int index) {
        this.isExit = false;
        this.index = index;
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
     * Executes the given command to delete a task.
     *
     * @param list TaskList before deletion of task.
     * @param ui Ui object to log the execution of the command.
     * @param storage Storage object that references the path to store the updated list of tasks.
     * @throws DukeException If index provided is invalid.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task removed = list.deleteAtIndex(this.index);
        if (removed != null) {
            String message =
                    "Noted.I've removed this task:\n" +
                    "  " + removed + "\n" +
                    String.format("Now you have %d tasks in the list.", list.size());
            ui.printMessage(message);
            storage.save(list.convertToStorageString());
        } else {
            throw new InvalidIndexException();
        }
    }
}
