import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list that contains a <code>List</code> of tasks.
 * 
 * @author Sherman Ng Wei Sheng
 */
public class TaskList {
    private final List<Task> list;

    /**
     * Constructor to initialise a new TaskList
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Method to add a new task to the list.
     * 
     * @param task Task to be added
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Method to retrieve the size of the list.
     * 
     * @return The size of the TaskList.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Method to obtain the Task at the given index.
     * 
     * @param index The index to retrieve the Task from in the TaskList.
     * @return The Task that is located at the given index.
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * If given a valid index, returns true and mark the Task as done.
     * If given an invalid index, returns false.
     * 
     * @param index The given index to mark the Task located there as done.
     * @return A boolean value, true if successfully marked as done and false if invalid index given.
     */
    public boolean markDoneAtIndex(int index) {
        if (index > this.size()-1 || index < 0) {
            return false;
        } else {
            Task taskToBeMarkDone = this.get(index);
            taskToBeMarkDone.markAsDone();
            return true;
        }
    }

    /**
     * Given a valid index, remove the task at the index and return that Task.
     * 
     * @param index The index of the task to be removed.
     * @return The task that is removed.
     */
    public Task deleteAtIndex(int index) {
        if (index > this.size()-1 || index < 0) {
            return null;
        } else {
            return this.list.remove(index);
        }
    }
}
