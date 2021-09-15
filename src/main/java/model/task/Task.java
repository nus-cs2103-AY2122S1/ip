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
     * @param description the description of the models.task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Default constructor for Task to be used by its subclasses for convenience.
     * Abstract Task cannot be created.
     *
     * @param description the description of the models.task
     * @param isDone      whether the models.task is done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Getter for isDone.
     *
     * @return isDone boolean of the models.task
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Getter for description.
     *
     * @return description of the models.task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Return a string representation of isDone boolean.
     *
     * @return X for true (done) and " " for false (not done)
     */
    public String getStatusIcon() {
        return (isDone ? "✔" : " ✘ ");
    }

    /** mark the current models.task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    /**
     * Template: "[x] description" or "[ ] description" for done and not done models.task respectively.
     *
     * @return the template of the string representation of models.task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
