package duke;

/**
 * This class encapsulates the command to complete a task.
 * It is triggered by the parser and uses the TaskManager, Ui, and Storage.
 */
public class DoneCommand implements ICommand {

    String taskIndex;
    TaskManager tm;
    Ui ui;
    Storage storage;
    Task completedTask;
    String reply;

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
            completedTask = tm.completeTask(taskIndex);
            this.ui = ui;
            this.tm = tm;
            this.storage = storage;
            if (completedTask == null) {
                reply = ui.getInvalidIndexMessage();
            } else {
                reply = ui.getTaskCompletionMessage(completedTask, tm.getTasks().size());
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
