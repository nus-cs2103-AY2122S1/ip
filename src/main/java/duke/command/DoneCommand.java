package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * This class abstracts the done command that the user wants to execute.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private final Integer[] tasksToMark;

    /**
     * Constructs a DoneCommand that will mark the task at the given task index once executed.
     *
     * @param tasksToMark The index of the task to be marked as done.
     */
    public DoneCommand(Integer[] tasksToMark) {
        //Sort and remove duplicate Integers
        TreeSet<Integer> tree = new TreeSet<>(Arrays.asList(tasksToMark));
        this.tasksToMark = new Integer[tree.size()];;
        tree.toArray(this.tasksToMark);
    }

    /**
     * Execute the command to mark the given task as done.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     * @throws DukeException The checked exception to be thrown when execution fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        StringBuilder doneTasks = new StringBuilder();
        for (int i = tasksToMark.length - 1; i >= 0; i--) {
            int taskNum = tasksToMark[i];
            if (taskNum < 0 || tasks.size() <= taskNum) {
                throw new DukeException("OOPS!!! Please enter a valid task number");
            }
            Task taskToBeDone = tasks.get(taskNum);
            assert taskToBeDone != null;
            taskToBeDone.markAsDone();
            doneTasks.append(taskToBeDone).append("\n ");
        }
        storage.update(tasks);
        return "Nice! I've marked these tasks as done:\n "
                + doneTasks;
    }
}
