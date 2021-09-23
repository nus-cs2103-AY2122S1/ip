package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents outstanding task for the user to keep track.
 */
public class Task {

    protected static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyy HH:mm");

    protected LocalDateTime time = null;
    private String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
        assert(!description.isEmpty());
    }

    protected Task(String description, String time) throws DukeException {
        this(description);
        try {
            this.time = LocalDateTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect Time Format!");
        }
        assert(!description.isEmpty());
        assert(!time.isEmpty());
    }

    /**
     * Mark task as done.
     */
    public void finishTask() {
        isDone = true;
    }

    /**
     * Check if a task is done, in a list.
     * @return Indictor if task is done
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    /**
     * Format task to be saved in file.
     * @return Formatted string representation of task.
     */
    public String toSaveString() {
        return String.format("%s|%s|%s", isDone, description, time);
    }

    /**
     * Get the time of the task.
     * @return Time.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Format task to be displayed in list.
     * @return Formatted string representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) otherObject;
        boolean isDescriptionEquals = this.description.equals(otherTask.description);
        boolean bothTimesExist = (this.time != null && otherTask.time != null);
        if (bothTimesExist) {
            boolean isTimeEquals = this.time.equals(otherTask.time);
            return (isDescriptionEquals && isTimeEquals);
        }
        return isDescriptionEquals;
    }

}
