import java.util.ArrayList;

/**
 * This class encapsulates a Duke task list.
 * It is responsible for maintaining the tasks in an instance of Duke.
 *
 */
public class TaskList {
    private ArrayList<Task> taskList;

    // Default constructor
    TaskList() {
        this.taskList = new ArrayList<>();
    }

    // If there is already a saved task list
    TaskList(ArrayList<Task> initialTaskList) {
        this.taskList = initialTaskList;
    }

    /**
     * Adds a task to the task list
     * @param task The task to be added.
     *
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a task from the task list
     * @param index The index of the task to be removed.
     *
     */
    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * Get the task from the specified index
     * @param index The index of the task to get.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the total number of tasks in the task list
     * @return Number of tasks in the task list
     */
    public int numberOfTasks() {
        return this.taskList.size();
    }
}

