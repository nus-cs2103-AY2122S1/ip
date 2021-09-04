package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Task class encapsulates a task with a name and an indicator of its completion.
 * As every Task is one of various types of Tasks, this class is abstract.
 */
public abstract class Task {

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    private String name;
    private boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param name the name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns a string representing the task.
     *
     * @return a string representing the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.name;
        }
        return "[ ] " + this.name;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns a String that will be stored in the taskList.txt file.
     *
     * @return a String to be stored in the taskList.txt file.
     */
    public abstract String printToFile();

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.isDone;
    }

    protected String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
