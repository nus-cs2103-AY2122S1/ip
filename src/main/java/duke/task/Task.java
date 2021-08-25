package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

abstract public class Task {
    protected boolean completed;
    protected String description;

    /**
     * Constructor for a Task.
     *
     * @param description of the Task.
     */
    protected Task(String description) {
        this.description = description;
        this.completed = false;
    }

    @Override
    public String toString() {
        String check = this.completed ? "[X] " : "[ ] ";
        return check + description;
    }

    /**
     * Formats LocalDate time into "MMM d yyyy"
     *
     * @param time LocalDate to be formatted.
     * @return String formatted LocalDate.
     */
    public static String printTime(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.completed = true;
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t\t " + this + "\n");
    }

    /**
     * Generates a String for storing the Task.
     *
     * @return String for storing the Task.
     */
    abstract public String storageString();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return completed == task.completed && description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(completed, description);
    }
}
