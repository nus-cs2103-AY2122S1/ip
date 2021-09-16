package duke.task;
import java.util.ArrayList;

/**
 * Class that encapsulates Tasklists.
 */
public class TaskList {
    private ArrayList<Task> tasklist;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasklist = new ArrayList<Task>();
    }

    /**
     * Adds task to current TaskList.
     *
     * @param task Task to be added to current TaskList.
     */
    public void addTask(Task task) {
        this.tasklist.add(task);
    }

    /**
     * Removes task from current TaskList.
     *
     * @param index Index of the task to be removed from current TaskList.
     */
    public void removeTask(int index) {
        this.tasklist.remove(index);
    }

    /**
     * Retrieves task from current TaskList by their index.
     *
     * @param index Index of the task to be retrieved.
     * @return Task at the index.
     */
    public Task getTask(int index) {
        return this.tasklist.get(index);
    }

    /**
     * Returns the number of items in the current TaskList.
     *
     * @return Number of items in the current TaskList.
     */
    public int getSize() {
        return this.tasklist.size();
    }

}
