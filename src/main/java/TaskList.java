import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

/**
 * Represents a list that contains a <code>List</code> of tasks.
 * 
 * @author Sherman Ng Wei Sheng
 */
public class TaskList {
    private final List<Task> list;
    private final Storage storage;

    /**
     * Constructor to initialize a new TaskList
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.storage = new Storage();
    }

    /**
     * Adds a new task to the list.
     * 
     * @param task Task to be added
     */
    public void add(Task task) {
        this.list.add(task);
        this.saveToStorage();
    }

    /**
     * Returns the size of the list.
     * 
     * @return The size of the TaskList.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Returns the Task at the given index.
     * 
     * @param index The index to retrieve the Task from in the TaskList.
     * @return The Task that is located at the given index.
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Marks the task at the index as done.
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
            this.saveToStorage();
            return true;
        }
    }

    /**
     * Removes the task at the given index and returns that Task.
     * 
     * @param index The index of the task to be removed.
     * @return The task that is removed.
     */
    public Task deleteAtIndex(int index) {
        if (index > this.size()-1 || index < 0) {
            return null;
        } else {
            Task removedTask = this.list.remove(index);
            this.saveToStorage();
            return removedTask;
        }
    }
    
    public String generateMessage() {
        int listSize = this.list.size();

        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < listSize; i++) {
            int index = i + 1;
            Task content = this.list.get(i);
            message.append("\n").append(index).append(".").append(content);
        }
        return message.toString();
    }
    
    public void loadFromStorage() {
        this.storage.loadDataTo(this);
    }
    
    public void saveToStorage() {
        this.storage.saveDataFrom(this);
    }
}
