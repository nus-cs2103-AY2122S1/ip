import java.io.IOException;

/**
 * This class encapsulates a command to add a Task to a TaskList.
 * Extends Command class.
 */
public class AddTaskCommand extends Command {

    private Task task;

    /**
     * Constructor for a AddTaskCommand.
     * @param taskList TaskList from which to delete Task.
     * @param ui Ui involved in the command.
     * @param task Task to be added.
     */
    public AddTaskCommand(TaskList taskList, Storage storage, Ui ui, Task task) {
        super(taskList, storage, ui);
        this.task = task;
    }

    /**
     * Executes the Command.
     */
    @Override
    public void execute() {
        taskList.add(task);
        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.showError("Error: Unable to Save\n" + e.getMessage());
        }
        ui.showMessage("Sure thing. Added to list:\n" + task + "\nNumber of tasks in list: " + taskList.getSize());
    }

}
