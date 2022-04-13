package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeIncorrectTaskDescription;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Represents a Deadline object that can be added
 * to users' task list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Deadline extends Task {
    private LocalDate deadlineDate;

    /**
     * Constructor for the Deadline task.
     * @param arrString user input sliced into an array.
     * @throws DukeException if Duke cannot create the Deadline object.
     */
    protected Deadline(String[] arrString) throws DukeException {
        super(arrString.length < 2 ? " " : arrString[0]);
        String date = arrString[1] == null ? " " : arrString[1].strip();

        try {
            this.deadlineDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeIncorrectTaskDescription(this, new IllegalArgumentException());
        }
    }

    /**
     * String representation of the Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " +
                this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String saveToFile() {
        return "D | " + super.saveToFile() + "| " + this.deadlineDate;
    }

    /**
     * Returns the date for the deadline.
     * @return date for deadline.
     */
    public LocalDate getDate() {
        return this.deadlineDate;
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof ToDo) {
            return this.SMALLER;
        }

        if (task instanceof Event) {
            return this.SMALLER;
        }

        @SuppressWarnings("unchecked") // task must be a deadline
        Deadline d = (Deadline) task;

        return this.getDate().compareTo(d.getDate());
    }
}