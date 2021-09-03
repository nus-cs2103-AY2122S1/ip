package duke;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * Array List of Task objects.
 */
public class TaskArrayList extends ArrayList<Task> {
    public static final String DELETE_USAGE_TEXT = "Usage: delete <integer task number>";
    public static final String DONE_USAGE_TEXT = "Usage: done <integer task number>";
    public static final String FIND_USAGE_TEXT = "Usage: find <search String>";

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
     * Marks the given task as done.
     *
     * @param taskNo task number to mark as done.
     * @return removal message String.
     * @throws DukeException when invalid task number provided.
     */
    public String deleteTask(int taskNo) throws DukeException {
        if (taskNo > this.size()) {
            throw new DukeException(String.format("task %d not found", taskNo));
        }
        Task toDel = this.get(taskNo - 1);
        this.remove(taskNo - 1);
        return "removed : " + toDel.toString() + "\n" + this.announceNewLength();
    }

    /**
     * Generates the message to print when tasklist length changes.
     *
     * @return You now have X task(s) in the list.
     */
    private String announceNewLength() {
        return String.format("Now you have %d %s in the list.", this.size(), this.size() == 1 ? "task" : "tasks");
    }

    /**
     * Enumerates members for printing
     *
     * @return String[] of "X. taskname".
     */
    public String enumerate() {
        String out = "";
        int num = 0;
        for (Task task : this) {
            out += String.format("%d. ", num + 1) + task.toString() + "\n";
            num++;
        }
        return out;
    }

    /**
     * Find all tasks that contain the searchTerm
     * enumerate but only include if task string contains search term
     * use absolute task number so delete and done commands can reference this result
     *
     * @param searchTerm term to find in task names
     * @return String[] of tasks of "X. taskname"
     */
    public String find(String searchTerm) {
        String out = "";
        int num = 0;
        for (Task task : this) {
            if (task.toString().contains(searchTerm)) {
                out += String.format("%d. ", num + 1) + task.toString() + "\n";
            }
            num++;
        }
        return out;
    }

    /**
     * Marks a task as done.
     *
     * @param index task number to mark.
     * @return mark done message String.
     * @throws DukeException when invalid task number provided.
     */
    public String markDone(int index) throws DukeException {
        if (index > this.size()) {
            throw new DukeException(String.format("task %d not found", index));
        }
        this.get(index - 1).markDone();
        return "Nice! I've marked this task as done:\n"
                + this.get(index - 1).toString();
    }
}
