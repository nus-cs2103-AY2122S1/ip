package duke.task;

import duke.main.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
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
     * Parse the given time.
     *
     * @param time String to parse.
     * @return LocalDate object corresponding to the given time.
     * @throws DateTimeParseException if the time is incorrectly formatted.
     */
    public static LocalDate parseTime(String time) throws DateTimeParseException {
        try {
            return LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("\t☹ OOPS!!! Please specify the time in the yyyy-mm-dd format.\n");
        }
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

    /**
     * Checks if the task contains any keyword.
     *
     * @param keywords to match.
     * @return true if a match is found, else false.
     */
    public boolean containsKeyword(String[] keywords) {
        return Arrays.stream(keywords).anyMatch(keyword -> description.contains(keyword));
    }

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
