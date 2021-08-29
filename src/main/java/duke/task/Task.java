package duke.task;

/**
 * Abstract class that is extended by Deadline, Event and Todo.
 * It contains methods to obtain/alter information about the Task object.
 *
 * @author Gu Geng
 */
public abstract class Task {
    private String content;
    private boolean ifCompleted;

    /**
     * Initialises the Task with the given information.
     *
     * @param content A String that contains information that can possibly create a Task Object.
     */
    Task(String... content) {
        this.content = content[1];
        ifCompleted = false;
    }

    public String getContent() {
        return content;
    }

    public boolean getStatus() {
        return ifCompleted;
    }

    /**
     * Sets the Task as done.
     */
    public void doneTask() {
        ifCompleted = true;
    }

    /**
     * Overrides the toString method.
     *
     * @return A String representation of the Task object in specified format.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                ifCompleted ? "x" : " ", content);
    }

    public abstract String record();
    public abstract boolean hasSchedule();
    public abstract String getType();
    public abstract String getTime();

}
