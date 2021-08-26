package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The TaskList class encapsulates a list of tasks.
 */
public class TaskList {
    /** An arraylist of Tasks to note all the Tasks stored in the TaskList. */
    private ArrayList<Task> allTasks;
    /** Pointer to indicate the next position to store the task in the arraylist. */
    private int nextSpaceToStore;

    /**
     * Constructor to initialise a TaskList.
     */
    public TaskList() {
        ArrayList<Task> allTasks = new ArrayList<>();
        this.allTasks = allTasks;
        this.nextSpaceToStore = 0;
    }

    /**
     * Constructor to initialise a TaskList.
     */
    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
        this.nextSpaceToStore = allTasks.size();
    }

    /**
     * Stores the task to the TaskList and increment the nextSpaceToStore pointer by one.
     * @param toStore The task to store into the TaskList.
     */
    public void storeTask(Task toStore) {
        allTasks.add(toStore);
        nextSpaceToStore = nextSpaceToStore + 1;
    }

    /**
     * Indicates the specified task is done in the TaskList.
     * @param taskNo The task that is completed.
     */
    public void doneTask(int taskNo) {
        Task doneTask = allTasks.get(taskNo - 1);
        doneTask.completeTask();
    }

    /**
     * Deletes the specified task from the TaskList.
     * @param taskNo The task to be deleted.
     */
    public void deleteTask(int taskNo) {
        allTasks.remove(taskNo - 1);
        nextSpaceToStore = nextSpaceToStore - 1;
    }

    /**
     * Returns the next position to store the task in the tasklist.
     * @return The next position to store the task in the tasklist.
     */
    public int getNextSpaceToStore() {
        return this.nextSpaceToStore;
    }

    /**
     * Returns the entire ArrayList the tasks are stored in.
     * @return The entire ArrayList the tasks are stored in.
     */
    public ArrayList<Task> getAllTasks() {
        return this.allTasks;
    }

    /**
     * Returns the specified task from the tasklist.
     * @param taskNo The task to be retrieved.
     * @return The specified task from the tasklist.
     */
    public Task getSpecificTask(int taskNo) {
        return allTasks.get(taskNo - 1);
    }

    /**
     * Returns the string representation of the number of tasks in the TaskList.
     * @return String representation of the number of tasks in the TaskList.
     */
    @Override
    public String toString() {
        return "Now you have " + this.nextSpaceToStore + " tasks in the list.";
    }
}
