package src.main.java.duke;

/**
 * Class to represent a task with String task name and
 * current progress of the task.
 */

public class Task {

    private String task;
    private boolean isDone;

    Task(String T, boolean D) {
        task = T;
        isDone = D;
    }

    /**
     * Method to mark the task as done.
     */
    void markAsDone() {
        this.isDone = true;
    }


    /**
     * getter for private instance variable.
     *
     * @return String value task name.
     */
    String getTask() {
        return task;
    }

    /**
     * method to set the time in the task
     *
     * @param time time in String to be set
     * @throws DukeException
     */
    void setTime(String time) throws DukeException {
        throw new TimeNotFoundException("Given task doesn't have time");
    }

    /**
     * to give the String representation of the task.
     *
     * @return
     */
    @Override
    public String toString() {
        return "| " + (isDone ? "1" : "0") + " | " + task;
    }
}
