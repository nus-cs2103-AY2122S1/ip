package Duke;

/**
 * This class encapsulates the command to complete a task.
 * It is triggered by the parser and uses the TaskManager, Ui, and Storage.
 */
public class DoneCommand implements ICommand {

    String taskIndex;

    /**
     * Constructor for the command.
     *
     * @param taskIndex The index of the task which the user wants to mark completed.
     */
    public DoneCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the desired task completed by interacting with the relevant instances as mentioned above.
     *
     * @param tm The TaskManager object controlling the tasks in Duke.
     * @param ui The Ui object managing Duke's user interface.
     * @param storage The Storage object managing the local storing of tasks.
     */
    public void execute(TaskManager tm, Ui ui, Storage storage) {
        try {
            Task completedTask = tm.completeTask(taskIndex);
            if (completedTask == null) {
                ui.printInvalidIndexMessage();
            } else {
                ui.printTaskCompletion(completedTask, tm.getTasks().size());
                storage.updateSave(tm.getTasks());
            }
        } catch (DukeException.InvalidInputException e) {
            ui.printErrorMessage(e);
        }
    }

}
