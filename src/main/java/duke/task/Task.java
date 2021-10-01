package duke.task;

import java.time.LocalDate;

import duke.exception.DukeException;

/**
 * A class that represents tasks.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The variable indicating whether the task is done.
     */
    protected boolean isDone = false;

    /**
     * Constructs a {@code Task} object by the description. Throw a {@code DukeException} if the description is empty.
     *
     * @param description The description you want to store.
     * @throws DukeException If the description is empty.
     */
    public Task(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("Your description cannot be empty!");
        }
        this.description = description;
    }

    /**
     * Constructs a {@code Task} object by the description and specifying if it is done. Throws a {@code DukeException}
     * if the description is empty.
     *
     * @param description The description you want to store.
     * @param isDone      Whether the task is done.
     * @throws DukeException If the description is empty.
     */
    public Task(String description, boolean isDone) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("Your description cannot be empty!");
        }
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Transforms the task to a single line that can be stored in a txt file.
     *
     * @return A single line that can be stored in a txt file.
     */
    public String taskToLine() {
        String isDone = this.isDone ? "1" : "0";
        return String.format(" | %s | %s", isDone, this.description);
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the starting or due time of the task, if possible. If there is no time related to the task, return {@code
     * null}. In this case, it will always return {@code null}.
     *
     * @return null
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Sets the date of the task, if possible.
     */
    public void setDate(LocalDate localDate) {
        return;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns {@code "[X]"} is the task is done and {@code "[ ]"} otherwise.
     *
     * @return An icon indicating whether the task is done.
     */
    public String markDoneIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return this.markDoneIcon() + " " + this.description;
    }

    /**
     * The three type of tasks.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
}
