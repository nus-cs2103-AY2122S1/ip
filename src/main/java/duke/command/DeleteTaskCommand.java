package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskException;
import duke.task.Task;
import duke.ui.Ui;

public class DeleteTaskCommand extends Command {
    private int taskIndex;

    /**
     * Creates a command that deletes a task.
     *
     * @param arguments Command arguments.
     */
    public DeleteTaskCommand(String arguments) throws InvalidCommandException {
        if (arguments.length() == 0) {
            throw new InvalidCommandException("Command `delete` requires an arguments");
        }

        try {
            this.taskIndex = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Unable to parse number from arguments: " + arguments);
        }
    }

    /**
     * Delete a task from the user's tasklist.
     *
     * @param taskList The tasklist.
     * @param ui The instance of the {@link Ui} class.
     * @param storage The instance of the {@link Storage} class.
     * @throws Exception when unable to find task or when unable to save tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task;
        try {
            task = taskList.removeTask(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException("There is no task with the following number: " + taskIndex);
        }

        storage.saveTasks(taskList);
        ui.printMessage("Removed the following task:\n    " + task.toString() + "\n" + "You now have "
                + taskList.size() + " tasks in your list.");
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
