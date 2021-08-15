/**
 * A Task object represent a task in Duke
 * A Task contains a description and a check for whether it has been done
 */

public class Task {

    // The string that describes the task
    String desc;
    boolean isDone;

    Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Sets the task as done
     */
    void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as not done
     */
    void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + desc;
    }
}
