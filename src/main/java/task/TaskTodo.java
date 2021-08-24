package task;

import java.time.LocalDate;

/**
 * To-do task event
 */
public class TaskTodo extends Task {

    /**
     * Constructor
     *
     * @param description Description of task
     * @param done Completion status
     */
    public TaskTodo(String description, boolean done) {
        super(description, done);
    }

    /**
     * String representation of to-do
     *
     * @return to-do display
     */
    @Override
    public String toString() {
        String checkBox = isDone
                ? "[X] "
                : "[ ] ";
        return "[T]" + checkBox + description;
    }

    /**
     * The string representation of Task to be used for saving
     *
     * @return Save string
     */
    @Override
    public String saveString() {
        return "T" + '\t'
                + (this.isDone ? "1" : "0") + '\t'
                + this.description;
    }

    /**
     * Checks if the task falls on a given date
     *
     * @param date Date to check
     * @return false (To-dos have no date)
     */
    @Override
    public boolean isDate(LocalDate date) {
        return false;
    }
}
