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
     * Lists out the current tasks in the TaskList.
     */
    public void listTask() {
        for (int i = 0; i < nextSpaceToStore; i++) {
            int currTask = i + 1;
            System.out.println(currTask + "." + allTasks.get(i).toString());
        }
    }

    /**
     * Indicates the specified task is done in the TaskList.
     * @param taskNo The task that is completed.
     */
    public void doneTask(int taskNo) {
        Task doneTask = allTasks.get(taskNo - 1);
        doneTask.completeTask();
        System.out.println("  " + doneTask.toString());
    }

    /**
     * Deletes the specified task from the TaskList.
     * @param taskNo The task to be deleted.
     */
    public void deleteTask(int taskNo) {
        Task deleteTask = allTasks.get(taskNo - 1);
        allTasks.remove(taskNo - 1);
        nextSpaceToStore = nextSpaceToStore - 1;
        System.out.println("  " + deleteTask.toString());
    }

    public ArrayList<Task> getAllTasks() {
        return this.allTasks;
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
