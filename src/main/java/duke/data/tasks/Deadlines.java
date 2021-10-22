package duke.data.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a Deadline, a Task that a due date of type LocalDate.
 */
public class Deadlines extends Task {
    private final LocalDate date;

    /**
     * Constructs a deadline task.
     * @param name The description of the deadline
     * @param date The date which the deadline is due
     */
    public Deadlines(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Constructs a deadline task with the given completion status.
     * @param completed Whether the deadline is completed
     * @param name The description of the deadline
     * @param deadline The date which the deadline is due
     */
    public Deadlines(boolean completed, String name, String deadline, String tags) {
        super(completed, name, tags);
        this.date = LocalDate.parse(deadline);
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String getSaveData() {
        if (this.isCompleted()) {
            return String.format("D~1~%s~%s~%s", this.getName(), this.date, this.getTags());
        } else {
            return String.format("D~0~%s~%s~%s", this.getName(), this.date, this.getTags());
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                this.date.format(
                        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deadlines)) return false;

        Deadlines deadline = (Deadlines) o;

        return this.getName().equals(deadline.getName())
                && this.isCompleted() == deadline.isCompleted()
                && this.date.equals(deadline.getDate());
    }
}
