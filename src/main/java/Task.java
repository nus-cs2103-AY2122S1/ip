/**
 * Class to represent a task with String task name and
 * current progress of the task
 */

public class Task {

    private String task;
    private boolean isDone;

    Task(String T, boolean D) {
        task = T;
        isDone = D;
    }

    /**
     * Method to mark the task as done
     */
    void markAsDone() {
        this.isDone = true;
    }

    /**
     * getter for private instance variable
     *
     * @return boolean value isDone
     */
    boolean getIsDone() {
        return isDone;
    }

    /**
     * getter for private instance variable
     *
     * @return String value task name
     */
    String getTask() {
        return task;
    }

    /**
     * to give the String representation of the task
     *
     * @return
     */
    @Override
    public String toString() {
        return "";
    }
}
