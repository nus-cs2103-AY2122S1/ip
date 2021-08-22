import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

class Task {
    /**
     * To indicate if a task is done.
     */
    public boolean isDone = false;
    public final String description;

    /**
     * Constructor for a task.
     *
     * @param description description for the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return this.description;
    }
}

class ToDo extends Task {

    /**
     * Constructor for a todo task.
     *
     * @param description description for the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * To String.
     *
     * @return To do's string representation
     */
    @Override
    public String toString() {
        return "[T]" + (this.isDone ? "[X]" : "[ ]") + " " + this.description;
    }
}

class DeadLine extends Task {
    LocalDate deadline;

    /**
     * Constructor for a deadline task.
     *
     * @param description description for the task.
     */
    public DeadLine(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Deadline's string.
     *
     * @return Deadline's string.
     */
    @Override
    public String toString() {
        return "[D]" + (this.isDone ? "[X]" : "[ ]") + " " + this.description + "(by: " + this.deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ")";
    }
}

class Event extends Task {
    LocalDate timePeriod;

    /**
     * Constructor for a event task.
     *
     * @param description description for the task.
     */
    public Event(String description, LocalDate timePeriod) {
        super(description);
        this.timePeriod = timePeriod;
    }

    /**
     * Event's string.
     *
     * @return Event's string.
     */
    @Override
    public String toString() {
        return "[E]" + (this.isDone ? "[X]" : "[ ]") + " " + this.description + "(at: " + this.timePeriod.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ")";
    }
}