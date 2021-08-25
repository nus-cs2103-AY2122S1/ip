package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates a list of Tasks.
 *
 * @author Kleon Ang
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructor for a new TaskList.
     */
    public TaskList() {
        super();
    }

    /**
     * Constructor to import existing Tasks into the TaskList.
     *
     * @param tasks A java.util.List of Tasks.
     */
    public TaskList(List<Task> tasks) {
        this.addAll(tasks);
    }

    /**
     * Adds a given task to the TaskList
     *
     * @param task The Task to be added into the TaskList.
     */
    public void addTask(Task task) {
        this.add(task);
        String addMessage = "Got it. I've added this task:\n  " + task
                + "\nNow you have " + this.size() + " tasks in the list.";
        Ui.printReply(addMessage);
    }

    /**
     * Deletes the task of the given index from the TaskList.
     *
     * @param counter The index of the Task to delete.
     * @throws DukeException A Duke-specific exception that occurs when index is out of bounds.
     */
    public void deleteTask(int counter) throws DukeException {
        if (counter <= 0 || counter > this.size()) {
            throw new DukeException("Sorry, no such task of index " + counter + ".");
        }
        Task taskToRemove = this.remove(counter - 1);
        Ui.printReply("Noted. I've removed this task:\n  " + taskToRemove
                + "\nNow you have " + this.size() + " tasks in the list.");
    }
}
