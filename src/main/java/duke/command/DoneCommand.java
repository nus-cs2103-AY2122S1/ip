package duke.command;

import duke.Pair;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * This class represents a {@code DoneCommand}. User input for a
 * {@code DoneCommand} starts with "done".
 *
 * @author Elizabeth Chow
 */
public class DoneCommand extends Command {
    /**
     * Constructor for a {@code DeleteCommand}
     *
     * @param args {@code String} array with length 1. {@code args[0]} contains the
     *             task number to be marked as done.
     */
    public DoneCommand(String[] args) {
        super(args);
    }

    /**
     * Marks a {@code Task} as done and writes to storage. If task is
     * already done, ui will display an error message.
     *
     * @param tasks   the tasklist to be modified.
     * @param ui      responsible for printing to the terminal.
     * @param storage stores all the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskNo = Integer.parseInt(args[0]);
        Pair<Boolean, Task> statusTaskPair = tasks.markTaskDone(taskNo);
        boolean isTaskAlreadyDone = statusTaskPair.getFirst();
        Task task = statusTaskPair.getSecond();

        if (isTaskAlreadyDone) {
            ui.showError(String.format("Task %s is already done!\n  %s", taskNo + 1, task));
        } else {
            ui.showDoneTask(task);
        }
        storage.writeToFile(tasks);
    }

    /**
     * Returns {@code false}. Program should not terminate after
     * {@code DoneCommand}.
     *
     * @return {@code false}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
