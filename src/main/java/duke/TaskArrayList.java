package duke;

import java.util.ArrayList;

import duke.commands.CommandDelete;
import duke.commands.CommandDone;
import duke.commands.CommandFind;
import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * Array List of Task objects.
 */
public class TaskArrayList extends ArrayList<Task> {
    public static final String DELETE_USAGE_TEXT = CommandDelete.HELP_USAGE;
    public static final String DONE_USAGE_TEXT = CommandDone.HELP_USAGE;
    public static final String FIND_USAGE_TEXT = CommandFind.HELP_USAGE;

    public TaskArrayList() {
        super();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task object to add.
     * @return addition message String.
     */
    public String addTask(Task task) {
        this.add(task);
        return task.addMsg() + "\n" + this.announceNewLength();
    }

    /**
     * Checks if the provided task number exists in the list.
     *
     * @param taskNo Task number to check
     * @return true if there is a task with that task number in the list
     */
    private boolean isValidTaskNo(int taskNo) {
        return taskNo <= this.size();
    }

    /**
     * Marks the given task as done.
     *
     * @param taskNo task number to mark as done.
     * @return removal message String.
     * @throws DukeException when invalid task number provided.
     */
    public String deleteTask(int taskNo) throws DukeException {
        if (!isValidTaskNo(taskNo)) {
            throw new DukeException(String.format("task %d not found", taskNo));
        }
        int taskIndex = taskNo - 1;

        String toDel = this.get(taskIndex).toString();
        this.remove(taskIndex);

        return "removed : " + toDel + "\n"
                + this.announceNewLength();
    }

    /**
     * Generates the message to print when tasklist length changes.
     *
     * @return You now have X task(s) in the list
     */
    private String announceNewLength() {
        return String.format("Now you have %d %s in the list.", this.size(), this.size() == 1 ? "task" : "tasks");
    }

    /**
     * Enumerates members for printing.
     *
     * @return String[] of "X. taskname"
     */
    public String enumerate() {
        StringBuilder out = new StringBuilder();
        int taskIndex = 0;
        for (Task task : this) {
            out.append(String.format("%d. %s\n", taskIndex + 1, task.toString()));
            taskIndex++;
        }
        return out.toString();
    }

    /**
     * Find all tasks that contain the searchTerm.
     * enumerate but only include if task string contains search term
     * use absolute task number so delete and done commands can reference this result
     *
     * @param searchTerm term to find in task names
     * @return String[] of tasks of "X. taskname"
     */
    public String find(String searchTerm) {
        StringBuilder out = new StringBuilder();
        int taskIndex = 0;
        for (Task task : this) {
            if (task.toString().contains(searchTerm)) {
                out.append(String.format("%d. %s\n", taskIndex + 1, task.toString()));
            }
            taskIndex++;
        }
        return out.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param taskNo task number to mark
     * @return mark done message String
     * @throws DukeException when invalid task number provided
     */
    public String markDone(int taskNo) throws DukeException {
        if (!this.isValidTaskNo(taskNo)) {
            throw new DukeException(String.format("task %d not found", taskNo));
        }

        int taskIndex = taskNo - 1;
        assert this.get(taskIndex) instanceof Task;
        this.get(taskIndex).markDone();
        return "Nice! I've marked this task as done:\n"
                + this.get(taskIndex).toString();
    }
}
