package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;


public class Task {

    protected static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyy HH:mm");

    private String description;
    private boolean isDone;
    protected LocalDateTime time = null;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, String time) throws DukeException {
        this(description);
        try {
            this.time = LocalDateTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect Time Format!");
        }
    }

    public void finishTask() {
        isDone = true;
    }

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
    
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
