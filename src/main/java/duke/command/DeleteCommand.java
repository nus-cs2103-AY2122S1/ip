package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class that handles task-deletion command.
 */
public class DeleteCommand extends Command {

    private final int taskNum;

    /**
     * Construcyts a DeleteCommand object that handles task-deletion command.
     *
     * @param taskNum The number of the to-be-deleted task.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Returns the response after executing the task-deletion command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Get the task at specified index, delete it, and save the change.
        Task task = tasks.getTasks().get(taskNum - 1);
        tasks.delete(taskNum - 1);
        storage.save(tasks);

        return String.format("%s%s",
                "Noted. I've deleted this task:\n\t "
                + task,
                "\nNow you have "
                + tasks.getTaskNum()
                + " tasks in the list.");
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
