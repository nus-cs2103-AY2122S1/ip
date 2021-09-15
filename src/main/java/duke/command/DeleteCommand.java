package duke.command;

import java.util.Arrays;
import java.util.TreeSet;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;

/**
 * This class abstracts the delete command that the user wants to execute.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final Integer[] tasksToDelete;

    /**
     * Constructs a DeleteCommand that will delete the given task number when executed.
     *
     * @param tasksToDelete The indexes of the tasks to be deleted.
     */
    public DeleteCommand(Integer[] tasksToDelete) {
        //Sort and remove duplicate Integers
        TreeSet<Integer> tree = new TreeSet<>(Arrays.asList(tasksToDelete));
        this.tasksToDelete = new Integer[tree.size()];;
        tree.toArray(this.tasksToDelete);
    }

    /**
     * Execute the command to delete the task from the given TaskList.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     * @throws DukeException The checked exception to be thrown when execution fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        StringBuilder removedTasks = new StringBuilder();
        for (int i = tasksToDelete.length - 1; i >= 0; i--) {
            int taskNum = tasksToDelete[i];
            if (taskNum < 0 || tasks.size() <= taskNum) {
                throw new DukeException("OOPS!!! Please enter a valid task number");
            }
            removedTasks.append(tasks.remove(taskNum)).append("\n ");
        }
        storage.update(tasks);
        return "Got it. I've removed these tasks:\n "
                + removedTasks
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
