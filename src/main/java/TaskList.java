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
            System.out.println(currTask+ ". [" + allTasks[i].getStatusIcon()
                    + "] " + allTasks[i].getTaskDescription());
        }
    }

    /**
     * Method to indicate the indicated task is done in the TaskList.
     * @param taskNo The task that is completed.
     */
    public void done(int taskNo) {
        Task doneTask = allTasks[taskNo - 1];
        doneTask.taskCompleted();
        System.out.println("  [" + doneTask.getStatusIcon() + "] " + doneTask.getTaskDescription());
    }
}
