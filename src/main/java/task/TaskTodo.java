package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * To-do task event.
 */
public class TaskTodo extends Task {

    /**
     * Constructor.
     *
     * @param description Description of task.
     * @param done Completion status.
     */
    public TaskTodo(String description, boolean done, LocalDateTime dateTimeAdded) {
        super(description, done, dateTimeAdded);
    }

    /**
     * Returns the string representation of to-do.
     *
     * @return To-do display.
     */
    @Override
    public String toString() {
        String checkBox = isDone
                ? "[X] "
                : "[ ] ";
        return "[T]" + checkBox + description;
    }

    /**
     * Returns the string representation of Task to be used for saving.
     *
     * @return Save string.
     */
    @Override
    public String saveString() {
        return "T" + '\t'
                + (this.isDone ? "1" : "0") + '\t'
                + this.description + '\t'
                + this.dateTimeAdded;
    }

    /**
     * Checks if the task falls on a given date.
     *
     * @param date Date to check.
     * @return False (To-dos have no date).
     */
    @Override
    public boolean isDate(LocalDate date) {
        return false;
    }

    /**
     * Returns a LocalDate if present in task.
     * Since a to-do has no date, it should be prioritised after deadlines or events
     * that do contain dates, as they are most likely to be of higher priority.
     *
     * @return LocalDate.MAX to represent the lack of date.
     */
    @Override
    public LocalDate getDate() {
        return LocalDate.MAX;
    }

    /**
     * Returns a String representing time if present in task.
     *
     * @return Since no time is present, to-do returns an empty string.
     */
    @Override
    public String getTime() {
        return "";
    }

}
