package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task in DukeList.
 */
public abstract class Task {
    protected String name;
    protected LocalDate date;
    private boolean completed;

    public Task(String name) {
        this(name, false, null);
    }

    public Task(String name, boolean completed) {
        this(name, completed, null);
    }

    public Task(String name, LocalDate date) {
        this(name, false, date);
    }

    /**
     * Creates a task.
     *
     * @param name      of the task
     * @param completed whether the task is completed
     * @param date      of the task
     */
    public Task(String name, boolean completed, LocalDate date) {
        this.name = name;
        this.date = date;
        this.completed = completed;
    }

    /**
     * Marks the task as completed.
     */
    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Returns whether the task is completed.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Returns the name of the task.
     *
     * @return name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns date of the task. It could a deadline/date for the event.
     *
     * @return date of task
     */
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return ((Task) obj).name.equals(this.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.name;
    }

    /**
     * Gets the type of task. Used to save tasks' type into the database.
     *
     * @return type of the task
     */
    public TaskType getType() {
        return TaskType.byTask(this.getClass());
    }

    protected String getDateString() {
        return this.date == null ? "" : this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
