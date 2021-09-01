package duke;

/**
 * This class encapsulates the command to delete a task.
 * It is triggered by the parser and uses the TaskManager, Ui, and Storage.
 */
public class DeleteCommand implements ICommand {

    String taskIndex;
    TaskManager tm;
    Ui ui;
    Storage storage;
    Task deletedTask;
    String reply;


    /**
     * Constructor for the command.
     *
     * @param taskIndex The index of the task which the user wants deleted.
     */
    public DeleteCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the desired task by interacting with the relevant instances as mentioned above.
     *
     * @param tm The TaskManager object controlling the tasks in Duke.
     * @param ui The Ui object managing Duke's user interface.
     * @param storage The Storage object managing the local storing of tasks.
     */
    public void execute(TaskManager tm, Ui ui, Storage storage) {
        try {
            deletedTask = tm.deleteTask(taskIndex);
            this.ui = ui;
            if (deletedTask == null) {
                reply = ui.getInvalidIndexMessage();
            } else {
                reply = ui.getTaskDeletionMessage(deletedTask, tm.getTasks().size());
                storage.updateSave(tm.getTasks());
            }
        } catch (DukeException.InvalidInputException e) {
            reply = ui.getErrorMessage(e);
        }
    }

    public String getReply() {
        return reply;
    }
}