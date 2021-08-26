package duke;

/**
 * Represents a command class that adds a task.
 * 
 * @author Sherman Ng Wei Sheng
 */
public class AddCommand extends Command {
    
    private boolean isExit;
    private Task task;

    /**
     * Constructor for AddCommand.
     * 
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
        this.isExit = false;
    }

    /**
     * Returns true if the command is a programme terminating command.
     * 
     * @return True if it is a terminating command and false otherwise.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Adds a task to the TaskList provided, logs the action on console and updates the data in the local storage.
     * 
     * @param list TaskList before addition of the task.
     * @param ui Ui object to log the execution of the command.
     * @param storage Storage object that references the path to store the updated list of tasks.
     * @throws DukeException If problem encountered during saving.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        list.add(this.task);
        ui.printAddedTaskMessage(task, list);
        storage.save(list.convertToStorageString());
    }
}
