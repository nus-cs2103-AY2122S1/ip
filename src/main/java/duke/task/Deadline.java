package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.util.DukeException;

/**
 * Class that encapsulates a Deadline task.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Returns a new Deadline object.
     * @param deadlineName The user input.
     */
    public Deadline(String deadlineName) throws DukeException {
        super(deadlineName, 9, deadlineName.indexOf("/by ") - 1);
        int start = deadlineName.indexOf("/by ");
        dueDate = LocalDate.parse(deadlineName.substring(start + 4));
    }

    /**
     * Returns a new Deadline object.
     * @param deadlineName The Deadline as written in the save file.
     * @param isDone Whether the task is done.
     */
    public Deadline(String deadlineName, boolean isDone) {
        super(deadlineName, isDone, 0, deadlineName.indexOf("(by:") - 1);
        int start = deadlineName.indexOf("(by:") + 5;
        dueDate = LocalDate.parse(deadlineName.substring(start, start + 11),
                DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDeadlineDate(LocalDate date) {
        this.dueDate = date;
    }

    /**
     * Overrides the toString method in Task.
     * @return The String representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + getDueDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }

    /**
     * Overrides the equals method in Object.
     * @param o The Object to compare to.
     * @return If the objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline d = (Deadline) o;
            return d.getTaskName().equals(this.getTaskName())
                    && d.getDueDate().isEqual(this.getDueDate());
        }
        return false;
    }
}
