package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class that handles task-mark-as-done command.
 */
public class DoneCommand extends Command {

    private static final String errorMessage = "The number of the task must be at least 1.";
    private final int taskNum;

    /**
     * Constructs a DoneCommand that handles task-mark-as-done command.
     *
     * @param taskNum The number of the to-be-marked-as-done task.
     */
    public DoneCommand(int taskNum) {
        assert taskNum > 0 : "Task number should be at least 1.";
        this.taskNum = taskNum;
    }

    // Returns a response telling the user that the task has been successfully marked as done.
    private String createResponse(Task task) {
        String prefix = "Nice! I've marked this task as done:\n ";

        return String.format("%s%s", prefix, task);
    }

    /**
     * Returns the response after executing the task-mark-as-done command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTasks().get(taskNum - 1);
        task.markAsDone();
        storage.save(tasks);

        return createResponse(task);
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
