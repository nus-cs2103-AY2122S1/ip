import java.util.ArrayList;

/**
 * The TaskList class encapsulates a List of Tasks.
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
        this.allTasks = new ArrayList<>();
        this.nextSpaceToStore = 0;
    }

    /**
     * Method to store the task to the TaskList and increment the nextSpaceToStore pointer by one.
     * @param toStore The task to store into the TaskList.
     */
    public void store(Task toStore) {
        allTasks.add(toStore);
        nextSpaceToStore = nextSpaceToStore + 1;
    }

    /**
     * Method to list out the current tasks in the TaskList.
     */
    public void list() {
        for (int i = 0; i < nextSpaceToStore; i++) {
            int currTask = i + 1;
            System.out.println(currTask + "." + allTasks.get(i).toString());
        }
    }

    /**
     * Method to indicate the indicated task is done in the TaskList.
     * @param taskNo The task that is completed.
     */
    public void done(int taskNo) {
        Task doneTask = allTasks.get(taskNo - 1);
        doneTask.taskCompleted();
        System.out.println("  " + doneTask.toString());
    }

    /**
     * Method to delete the indicated task from the TaskList.
     * @param taskNo The task to be deleted.
     */
    public void delete(int taskNo) {
        Task deleteTask = allTasks.get(taskNo - 1);
        allTasks.remove(taskNo - 1);
        nextSpaceToStore = nextSpaceToStore - 1;
        System.out.println("  " + deleteTask.toString());
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
