package duke.commands;

import java.util.Set;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents a command that will mark a Task as done when executed.
 *
 * @author ruiquan
 */
public class DoneCommand extends Command {
    private final Set<Integer> indices;

    /**
     * Constructs a DoneCommand given a collection of indices.
     *
     * @param indices The indices of the tasks to be marked as done.
     */
    public DoneCommand(Set<Integer> indices) {
        super(false);
        this.indices = indices;
    }

    private boolean noSuchTaskExist(int index, TaskList tasks) {
        return index <= 0 || index > tasks.size();
    }

    /**
     * Executes the DoneCommand and mark the Task at the specified index
     * in the TaskList as done.
     *
     * @param tasks The collection of tasks.
     * @param storage The storage manager that deals with loading from and
     *               saving into a file.
     * @return The message representing the response.
     * @throws DukeException If the file that act as storage can not be found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        TaskList doneTasks = new TaskList();
        for (int index: indices) {
            if (noSuchTaskExist(index, tasks)) {
                continue;
            }
            Task doneTask = tasks.markTaskAsDone(index);
            assert doneTask.getStatusIcon().equals("X") : "The status of the task should be done";
            doneTasks.addTask(doneTask);
        }

        if (doneTasks.size() <= 0) {
            throw new DukeException("Looks like none of the the indices given are valid");
        }
        storage.save(tasks);
        String message = String.format("Nice! I've marked %s %s as done:\n%s\n",
                doneTasks.size() == 1 ? "this" : "these",
                doneTasks.size() == 1 ? "task" : "tasks",
                doneTasks);
        return message;
    }
}
