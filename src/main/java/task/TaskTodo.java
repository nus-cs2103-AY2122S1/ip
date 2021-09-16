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

    @Override
    public LocalDate getDate() {
        return LocalDate.MAX;
    }

    @Override
    public String getTime() {
        return "2400";
    }

}
