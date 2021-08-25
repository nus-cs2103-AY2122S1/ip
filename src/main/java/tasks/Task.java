package tasks;

import exceptions.*;

/**
 * A tasks.Task object represent a task in duke.Duke
 * A tasks.Task contains a description and a check for whether it has been done
 */

public class Task {

    // The string that describes the task
    String desc;
    boolean isDone;

    public Task(String desc, Boolean isDone) throws EmptyDescException {
        if (desc.isBlank()) {
            throw new EmptyDescException();
        }
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Sets the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as not done
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    String completionStatus() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public String saveText() {
        int isDone = this.isDone ? 1 : 0;
        return "T | " + isDone + " | " + desc + "\n";
    }

    @Override
    public String toString() {
        return this.completionStatus() + desc;
    }
}
