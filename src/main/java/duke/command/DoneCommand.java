package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class that handles task-mark-as-done command.
 */
public class DoneCommand extends Command {

    private final int taskNum;

    /**
     * Constructs a DoneCommand that handles task-mark-as-done command.
     *
     * @param taskNum The number of the to-be-marked-as-done task.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
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
        // Get the task at specified index, mark it as done, and save the change.
        Task task = tasks.getTasks().get(taskNum - 1);
        task.markAsDone();
        storage.save(tasks);

        return String.format("%s%s",
                "Nice! I've marked this task as done:\n\t",
                task);
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
