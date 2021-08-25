/**
 * Represents a processor that can delete a task from the task list. A subclass of the Processor class.
 */
public class DeleteTaskCommand extends Command {
    int taskIndex;
    /**
     * Constructor of the class `DeleteATaskProcessor`.
     */
    public DeleteTaskCommand(int taskIndex) {
        super("delete");
        this.taskIndex = taskIndex;
    }

    /**
     * Updates the message to be printed.
     *
     * @return Whether the program is still running.
     */
    @Override
    public boolean execute(TaskList taskList, Storage storage) throws InvalidTaskNoException {
        try {
            this.task = taskList.get(this.taskIndex);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNoException();
        }
        taskList.removeFromList(this.task);
        storage.removeFromFile(taskList.indexOf(this.task));
        this.message = String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %o tasks in the list.\n",
                this.task, taskList.getNumOfTasks());
        return true;
    }
}
