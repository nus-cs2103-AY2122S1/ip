package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Abstract template for a task
 */
abstract class Task {
    /**
     * Indicates if a task is done.
     */
    protected boolean isDone;
    protected final String DESCRIPTION;


    /**
     * Constructor for a task.
     *
     * @param DESCRIPTION description for the task.
     */
    public Task(String DESCRIPTION, boolean isDone) {
        this.DESCRIPTION = DESCRIPTION;
        this.isDone = isDone;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return this.DESCRIPTION;
    }
}

/**
 * A ToDo task.
 */
class ToDo extends Task {

    /**
     * Constructor for a todo task.
     *
     * @param description description for the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of a todo.
     *
     * @return To do's string representation
     */
    @Override
    public String toString() {
        return "[T]" + " | " + (this.isDone ? "[X]" : "[ ]") + " | " + this.DESCRIPTION;
    }
}

/**
 * A deadline task with a deadline.
 */
class DeadLine extends Task {
    private final LocalDate deadline;

    /**
     * Constructor for a deadline task.
     *
     * @param description description for the task.
     */
    public DeadLine(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of a deadline task.
     *
     * @return Deadline's string.
     */
    @Override
    public String toString() {
        return "[D]" + " | " + (this.isDone ? "[X]" : "[ ]") + " | " + this.DESCRIPTION + " | " + this.deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
}

/**
 * An event task with a event date.
 */
class Event extends Task {
    private final LocalDate timePeriod;

    /**
     * Constructor for a event task.
     *
     * @param description description for the task.
     */
    public Event(String description, LocalDate timePeriod, boolean isDone) {
        super(description, isDone);
        this.timePeriod = timePeriod;
    }

    /**
     * Returns the string representation of a event task.
     *
     * @return Event's string.
     */
    @Override
    public String toString() {
        return "[E]" + " | " + (this.isDone ? "[X]" : "[ ]") + " | " + this.DESCRIPTION + " | " + this.timePeriod.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
}