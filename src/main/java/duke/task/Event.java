package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /**
     * The time of the event.
     */
    protected LocalDate timeStart;

    /**
     * Constructs a {@code Event} object by the description and the due date. Throw a {@code DukeException} if the
     * description is empty.
     *
     * @param description The description you want to store.
     * @param timeStart   The time of the event.
     */
    public Event(String description, LocalDate timeStart) {
        super(description);
        this.timeStart = timeStart;
    }

    /**
     * Constructs a {@code Event} object by the description, the due date and specifying if it is done. Throws a {@code
     * DukeException} if the description is empty.
     *
     * @param description The description you want to store.
     * @param isDone      Whether the task is done.
     * @param timeStart   The time of the event.
     */
    public Event(String description, boolean isDone, LocalDate timeStart) {
        super(description, isDone);
        this.timeStart = timeStart;
    }

    @Override
    public void setDate(LocalDate localDate) {
        this.timeStart = localDate;
    }

    /**
     * Transforms the task to a single line that can be stored in a txt file.
     *
     * @return A single line that can be stored in a txt file.
     */
    @Override
    public String taskToLine() {
        return "E" + super.taskToLine() + " | " + this.timeStart;
    }

    /**
     * Returns the time of the event.
     *
     * @return The time of the event.
     */
    @Override
    public LocalDate getDate() {
        return this.timeStart;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String description = super.toString();
        return "[E]" + description + " (at: "
                + this.timeStart.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
