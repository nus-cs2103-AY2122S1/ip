package duke.command;

import duke.DukeException;
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
     * @param taskNo String representation of the task number.
     */
    public DoneCommand(String taskNo) {
        super(taskNo);
    }

    /**
     * Marks a {@code Task} as done and writes to storage. If task is
     * already done, ui will display an error message.
     *
     * @param tasks   the tasklist to be modified.
     * @param ui      responsible for printing to the terminal.
     * @param storage stores all the tasks.
     *
     * @return String message to be displayed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert args.length == 1 : "Done Command should only store 1 argument";
        int taskNo = Integer.parseInt(args[0]);
        Pair<Boolean, Task> statusTaskPair = tasks.markTaskDone(taskNo);
        storage.writeToFile(tasks);

        boolean isTaskAlreadyDone = statusTaskPair.getFirst();
        Task task = statusTaskPair.getSecond();

        if (isTaskAlreadyDone) {
            throw new DukeException(String.format("Task %s is already done!\n  %s", taskNo + 1, task));
        } else {
            return ui.showDoneTask(task);
        }
    }
}
