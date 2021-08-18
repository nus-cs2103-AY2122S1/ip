import java.util.ArrayList;

public class Task {

    private boolean completedStatus = false;
    private String name;
    private int taskNumber;
    private static int numberOfTask = 0;

    /**
     * Task type enum
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        NOTAPPLICABLE
    }

    /**
     * Constructor for task type
     * @param name name of task
     */
    public Task(String name) {
        this.name = name;
        numberOfTask += 1;
        this.taskNumber = numberOfTask;
    }

    /**
     *  Function used to make a task complete
     */
    public void completeTask() {
        this.completedStatus = true;
    }

    /**
     * String representation of task.
     * @return String. See above
     */
    public String toString() {
        String status;
        if (completedStatus) {
            status = "[✓] ";
        } else {
            status = "[X] ";
        }
       return status + name;
    }

    /**
     * Getter for the completion status of a task
     * @return
     */
    public boolean getCompletedStatus() {
        return this.completedStatus;
    }

    /**
     * Getter for the total number of task
     * @return
     */
    public static int getNumberOfTask() {
        return numberOfTask;
    }
}
