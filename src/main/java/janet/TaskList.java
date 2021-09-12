package janet;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the list of tasks tracked by Janet.
 */
public class TaskList implements Serializable {
    private final ArrayList<Task> tasks;

    /**
     * Class constructor which initialises a blank TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        assert(task != null);
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index from the list, and returns the deleted task.
     *
     * @param index Index of the task in the list of tasks
     * @return The task that has been deleted
     */
    public Task delete(int index) {
        assert(index <= tasks.size());
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks in list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task stored at a particular index in the list.
     *
     * @param index Index of the task in the list of tasks
     * @return Task found at that index in the list
     */
    public Task get(int index) {
        assert(index <= tasks.size());
        return tasks.get(index);
    }

    /**
     * Returns a new TaskList containing only elements from the original TaskList
     * which contain the query.
     *
     * @param query The string to be searched
     * @return The filtered TaskList
     */
    public TaskList find(String query) {
        assert(query != null);
        TaskList output = new TaskList();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).toString().contains(query)) {
                output.add(this.get(i));
            }
        }
        return output;
    }

    /**
     * Returns a new TaskList containing only elements from the original TaskList
     * which have the same date as the query.
     *
     * @param query The string of format YYYY-MM-DD
     * @return The filtered TaskList
     */
    public TaskList findByDate(String query) {
        LocalDate date = LocalDate.parse(query);
        TaskList output = new TaskList();
        for (int i = 0; i < this.size(); i++) {
            Task task = this.get(i);
            if (task instanceof Event && ((Event) task).atDate.equals(date)) {
                output.add(task);
            }
            if (task instanceof Deadline && ((Deadline) task).byDate.equals(date)) {
                output.add(task);
            }
        }
        return output;
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return "I did not find any tasks.";
        }
        String output = "Here's what I found:\n";
        for (int i = 0; i < this.size(); i++) {
            output += String.format("%d. %s\n", i + 1, this.get(i));
        }
        return output;
    }
}
