package duke;

/**
 * duke.Task that can be completed. Falls under three main cats, duke.Event, duke.Todo and deadline.
 */
public class Task {

    private static int numberOfTask = 0;
    private boolean isCompleteStatus = false;
    private String name;
    private int taskNumber;

    /**
     * duke.Task type enum
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
        this.isCompleteStatus = true;
    }

    /**
     * String representation of task.
     * @return String. See above
     */
    public String toString() {
        String status;
        if (isCompleteStatus) {
            status = "[done] ";
        } else {
            status = "[X] ";
        }
        return status + name;
    }

    /**
     * Getter for the completion status of a task
     * @return
     */
    public boolean getCompleteStatus() {
        return this.isCompleteStatus;
    }

    /**
     * Getter for the total number of task
     * @return
     */
    public static int getNumberOfTask() {
        return numberOfTask;
    }
}
