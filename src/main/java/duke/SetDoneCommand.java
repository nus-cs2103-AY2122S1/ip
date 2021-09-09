package duke;

/**
 * Represents the command to mark a task as done.
 */
public class SetDoneCommand extends Command {
    private int taskIndex;

    /**
     * Constructor for SetDoneCommand
     *
     * @param taskIndex Index of task to set as done.
     */
    public SetDoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks intended task as done, updates data file and shows in UI.
     *
     * @param tasks Task list component.
     * @param storage Storage component.
     * @param ui UI component.
     * @throws DukeException If an invalid task index was provided.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        if (tasks.getLength() <= taskIndex || 0 > taskIndex) {
            throw new DukeException("Invalid task index provided!");
        }
        Task currTask = tasks.getTask(taskIndex);
        currTask.markAsDone();
        assert currTask.getDoneStatus() : "Task should have been marked as done";
        storage.updateTasks(tasks);
        ui.showMarkedAsDone(currTask);
    }
}
