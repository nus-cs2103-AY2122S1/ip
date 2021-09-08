package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /**
     * The due date of the deadline.
     */
    protected LocalDate timeDue;

    /**
     * Constructs a {@code Deadline} object by the description and the due date. Throw a {@code DukeException} if the
     * description is empty.
     *
     * @param description The description you want to store.
     * @param timeDue     The due date of the deadline.
     */
    public Deadline(String description, LocalDate timeDue) {
        super(description);
        this.timeDue = timeDue;
    }

    /**
     * Constructs a {@code Deadline} object by the description, the due date and specifying if it is done. Throws a
     * {@code DukeException} if the description is empty.
     *
     * @param description The description you want to store.
     * @param isDone      Whether the task is done.
     * @param timeDue     The due date of the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDate timeDue) {
        super(description, isDone);
        this.timeDue = timeDue;
    }

    @Override
    public void setDate(LocalDate localDate) {
        this.timeDue = localDate;
    }

    /**
     * Returns the due time of the task.
     *
     * @return The due time of the task.
     */
    @Override
    public String taskToLine() {
        return "D" + super.taskToLine() + " | " + this.timeDue;
    }

    /**
     * Transforms the task to a single line that can be stored in a txt file.
     *
     * @return A single line that can be stored in a txt file.
     */
    @Override
    public LocalDate getDate() {
        return this.timeDue;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String description = super.toString();
        return "[D]" + description + " (by: "
                + this.timeDue.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
