package duke.task;

import duke.main.DukeException;
import duke.main.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Encapsulates a task.
 */
public abstract class Task {
    protected boolean isComplete;
    protected List<String> tags;
    protected String description;

    /**
     * Constructor for a Task.
     *
     * @param description of the Task.
     */
    protected Task(String description) {
        this.description = description;
        this.isComplete = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Parse the given time.
     *
     * @param time String to parseAndExecute.
     * @return LocalDate object corresponding to the given time.
     * @throws DateTimeParseException if the time is incorrectly formatted.
     */
    public static LocalDate parseTime(String time) throws DateTimeParseException {
        try {
            return LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Please specify the time in the yyyy-mm-dd format.\n");
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
     * Add a tag to the task.
     *
     * @param tag String to be added.
     */
    public String addTag(String tag) {
        tags.add(tag);
        return Ui.getTagAddedMessage(this);
    }

    /**
     * Format the tags into a String.
     *
     * @return String representation of tags.
     */
    public String formatTags() {
        String toPrint = " ";
        for (String tag : tags) {
            toPrint += ("#" + tag + " ");
        }
        return toPrint;
    }

    @Override
    public String toString() {
        String check = this.isComplete ? "[X] " : "[ ] ";
        return check + description + formatTags();
    }

    /**
     * Marks the Task as done.
     */
    public String markAsDone() {
        this.isComplete = true;
        return Ui.getTaskDoneMessage(this);
    }

    /**
     * Generates a String for storing the Task.
     *
     * @return String for storing the Task.
     */
    public abstract String generateStorageString();

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
        return isComplete == task.isComplete && description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isComplete, description);
    }
}
