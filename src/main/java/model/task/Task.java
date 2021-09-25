package model.task;

import java.util.Objects;

/**
 * Abstract class for Task.
 * The task is made of description and whether it is done or not.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.01
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Default constructor for Task to be used by its subclasses for convenience.
     * Abstract Task cannot be created.
     * isDone is set to false by default.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Default constructor for Task to be used by its subclasses for convenience.
     * Abstract Task cannot be created.
     *
     * @param description the description of the task.
     * @param isDone      whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Getter for isDone.
     *
     * @return isDone boolean of the task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Getter for description.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Return a string representation of isDone boolean.
     *
     * @return ✔ for true (done) and ✘ for false (not done).
     */
    public String getStatusIcon() {
        return (isDone ? "✔" : " ✘ ");
    }

    /** mark the current task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    /**
     * Check whether the given object is the same object, is of type Task and have matching description.
     *
     * @param other other object to be checked
     * @return whether that object should equal to this task or not
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) other;
        return otherTask.getDescription().equals(description);
    }

    /**
     * Template: "[x] description" or "[ ] description" for done and not done task respectively.
     *
     * @return the template of the string representation of task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
