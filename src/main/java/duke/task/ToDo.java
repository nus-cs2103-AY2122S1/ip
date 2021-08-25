package duke.task;

import duke.exception.DukeException;

public class ToDo extends Task {
    /**
     * Constructs a {@code ToDo} object by the description. Throw a {@code DukeException} if the description is empty.
     *
     * @param description The description you want to store.
     * @throws DukeException If the description is empty.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a {@code ToDo} object by the description and specifying if it is done. Throw a {@code DukeException}
     * if the description is empty.
     *
     * @param description The description you want to store.
     * @param isDone      Whether the task is done.
     * @throws DukeException If the description is empty.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Transforms the task to a single line that can be stored in a txt file.
     *
     * @return A single line that can be stored in a txt file.
     */
    @Override
    public String taskToLine() {
        return "T" + super.taskToLine();
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String description = super.toString();
        return "[T]" + description;
    }
}
