package duke;

/**
 * Represents a task with a description and a status.
 * @author Zhao Peiduo
 */
public class Task {
    private final String taskTitle;
    private boolean isDone;

    /**
     * The constructor for a Task Object.
     */
    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        //When a task is first created, it is by default not completed.
        this.isDone = false;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Customizes the string representation of a task object.
     *
     * @return string representation of a task in the form [][{X}] {description}
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + taskTitle;
        } else {
            return "[ ] " + taskTitle;
        }
    }

    /**
     * Creates the string representation to be saved in external txt file.
     *
     * @return String representation to be saved in external txt file.
     * */
    public String toStringRecord() {
        return this.toString();
    }

    /**
     * Two task objects are equal iff they have the same task title and same isDone value.
     *
     * @param another the object to be compared with
     * */
    @Override
    public boolean equals(Object another) {
        if (another instanceof Task) {
            Task anotherTask = (Task) another;
            return isDone == anotherTask.getIsDone() && taskTitle.equals(anotherTask.getTaskTitle());
        }
        return false;
    }
}
