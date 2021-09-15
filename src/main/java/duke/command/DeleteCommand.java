package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.CommandModifier;
import duke.util.ExceptionChecker;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class that handles task-deletion command.
 */
public class DeleteCommand extends Command {

    private final int taskNum;

    /**
     * Constructs a DeleteCommand object that handles task-deletion command.
     *
     * @param description The description of the to-be-deleted task.
     */
    public DeleteCommand(String description) throws DukeException {
        ExceptionChecker.checkEmptyDescription("delete", description);
        ExceptionChecker.checkInvalidDescription(description);
        this.taskNum = CommandModifier.toInt(description);
    }

    // Returns a response telling the user that the task has been successfully deleted.
    private String createResponse(TaskList tasks, Task task) {
        String prefix = "Hinjaku! Hinjaku!\nI've deleted this task:\n ";
        int taskNum = tasks.getTaskNum();
        String summary = "\nNow you have " + taskNum + " tasks in the list.";

        return String.format("%s%s", prefix + task, summary);
    }

    /**
     * Returns the response after executing the task-deletion command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ExceptionChecker.checkTaskExistence(taskNum, tasks.getTaskNum());
        Task task = tasks.getTasks().get(taskNum - 1);
        tasks.delete(taskNum - 1);
        storage.save(tasks);

        return createResponse(tasks, task);
    }

    /**
     * Returns the boolean false since it is not a command that exits the program.
     *
     * @return The boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
