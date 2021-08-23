package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

class Task {
    /**
     * Indicates if a task is done.
     */
    public boolean isDone;

    /**
     * Gets the description of the task.
     *
     * @return The Description of the task
     */
    public String getDescription() {
        return description;
    }

    public final String description;

    /**
     * Constructor for a task.
     *
     * @param description description for the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
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
        return this.description;
    }
}

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
        return "[T]" + " | " + (this.isDone ? "[X]" : "[ ]") + " | " + this.description;
    }
}

class DeadLine extends Task {
    LocalDate deadline;

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
        return "[D]" + " | " + (this.isDone ? "[X]" : "[ ]") + " | " + this.description + " | " + this.deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
}

class Event extends Task {
    LocalDate timePeriod;

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
        return "[E]" + " | " + (this.isDone ? "[X]" : "[ ]") + " | " + this.description + " | " + this.timePeriod.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
}