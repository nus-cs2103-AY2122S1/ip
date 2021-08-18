/**
 * The TaskList class encapsulates a List of Tasks.
 */
public class TaskList {
    /** An array of Tasks to note all the Tasks stored in the TaskList. */
    private Task[] allTasks;
    /** Pointer to indicate the next position to store the task in the array. */
    private int nextSpaceToStore;

    /**
     * Constructor to initialise a TaskList.
     * @param totalTasks The total number of tasks a TaskList can have.
     */
    public TaskList(int totalTasks) {
        this.allTasks = new Task[totalTasks];
        this.nextSpaceToStore = 0;
    }

    /**
     * Method to store the task to the TaskList and increment the nextSpaceToStore pointer by one.
     * @param toStore The task to store into the TaskList.
     */
    public void store(Task toStore) {
        this.allTasks[nextSpaceToStore] = toStore;
        this.nextSpaceToStore = this.nextSpaceToStore + 1;
    }

    /**
     * Method to list out the current tasks in the TaskList.
     */
    public void list() {
        for (int i = 0; i < this.nextSpaceToStore; i++) {
            int currTask = i + 1;
            System.out.println(currTask+ ". " + this.allTasks[i]);
        }
    }
}
