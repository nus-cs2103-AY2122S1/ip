package duke.task;

import java.time.LocalDateTime;

/**
 * Deadline class.
 * Used to represent a deadline task.
 *
 * @author KelvinSoo
 * @version Level-8
 *
 */
public class Deadline extends Task {
    private String dateLine;

    /**
     * A constructor to create a new deadline task.
     */
    public Deadline(String description, String dateLine) {
        super(description);
        this.dateLine = dateLine;
    }

    /**
     * A constructor to create a new deadline task.
     */
    public Deadline(String description, String dateLine, Boolean isDone) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
        this.dateLine = dateLine;
    }

    /**
     * A constructor to create a new deadline task with supported time format.
     */
    public Deadline(String description, LocalDateTime localDateTime) {
        super(description);
        this.dateLine = String.format("%s of %s %s, %s%s",
                localDateTime.getDayOfMonth(),
                localDateTime.getMonth().toString(),
                localDateTime.getYear(),
                localDateTime.getHour() < 12 ? localDateTime.getHour() : localDateTime.getHour() - 12,
                localDateTime.getHour() < 12 ? "am" : "pm");

    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String getMetaData() {
        return String.format("D|%s|%s", super.getMetaData(), dateLine);
    }

    @Override
    public String getDescription() {
        return String.format("%s (by: %s)", super.getDescription(), this.dateLine);
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), getDescription());
    }
}
