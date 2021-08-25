package duke.task;

import duke.Parser;
import duke.Ui;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class represents a deadline task.
 */

public class Deadline extends Task {
    /**
     * Deadline time of the task.
     */
    private final LocalDateTime deadlineTime;

    /**
     * Constructs a Deadline instance using the given description and deadline time.
     *
     * @param description  the given description.
     * @param deadlineTime the given deadline time.
     */
    public Deadline(String description, LocalDateTime deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Constructs a Deadline instance using the given description, complete state and deadline time.
     *
     * @param description  the given description.
     * @param isDone       the given complete state.
     * @param deadlineTime the given deadline time.
     */
    public Deadline(String description, boolean isDone, LocalDateTime deadlineTime) {
        super(description, isDone);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return a string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineTime.format(Ui.dateTimeFormatter) + ")";
    }

    /**
     * Returns a string representation of the deadline following .txt format.
     *
     * @return a string representation of the deadline following .txt format.
     */
    @Override
    public String toTxtFormat() {
        return "D" + Parser.SPLITER + super.toTxtFormat() +
                Parser.SPLITER + deadlineTime.format(Parser.inputDateTimeFormatter);
    }

    /**
     * Returns true if the given object is equal to this, otherwise false.
     *
     * @param o the given object.
     * @return true if the given object is equal to this, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Deadline deadline = (Deadline) o;
        return super.equals(o) && Objects.equals(deadlineTime, deadline.deadlineTime);
    }
}
