/**
 * This class implements a Task with a description and completion status
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class Task {
    /** Description of Task. */
    protected String description;

    /** Completion status of Task. */
    protected boolean isDone;

    /** Default constructor. */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to generate status icon for String representation of Task.
     *
     * @return Status icon 'x' for done ' ' for not done
     */
    public String getStatusIcon() {
        return isDone ? "x" : " ";
    }

    /**
     * Method to mark task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Return the string representation of Task
     *
     * @return The string representation of Task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
