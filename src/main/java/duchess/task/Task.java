package duchess.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duchess.main.DuchessException;

/**
 * This class implements a Task.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public abstract class Task {
    /** The DateTimeFormatter when converting time from string.*/
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy ha");
    /** The DateTimeFormatter when converting time with minutes from string.*/
    private static final DateTimeFormatter DATE_MINUTES_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy h:mma");
    /** The boolean to mark whether a task is completed.*/
    protected boolean isDone;
    /** The name of the task.*/
    protected String name;

    /**
     * Constructs a task.
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        setDoneStatus(false);
    }

    /**
     * Setter method to set a task as done or undone.
     * @param doneOrNot The boolean to set the task as.
     */
    public void setDoneStatus(boolean doneOrNot) {
        isDone = doneOrNot;
    }


    /**
     * Returns a simplified representation of the task for easier recovery from save file.
     * @return The file formatted string representation of the task.
     */
    public abstract String toFileFormat();

    /**
     * Checks if a task contains a certain keyword.
     * @param keyword The keyword to check in a task.
     * @return Whether the task contains part of or all of the keyword.
     */
    public boolean contains(String keyword) {
        return name.contains(keyword);
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract LocalDateTime getDateTimeStart();

    /**
     * Converts the user input string to a LocalDateTime.
     * @param by The user input date as a string.
     * @return The LocalDateTime representation.
     * @throws DuchessException When an incorrect format is used for Deadline.
     */
    public static LocalDateTime convertStringToDate (String by) throws DuchessException {
        try {
            String replacement = by.replace("am", "AM").replace("pm", "PM");
            return LocalDateTime.parse(replacement,
                    replacement.contains(":") ? DATE_MINUTES_FORMATTER : DATE_FORMATTER);
        } catch (DateTimeException e) {
            throw new DuchessException("Wrong format used.");
        }
    }

    /**
     * Returns a string representation of the task, with an [X] marked for done and [ ] as undone.
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[" + (isDone ? "X" : " ") + "] " + name);
    }
}
