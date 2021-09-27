package sora.task;

import java.time.LocalDateTime;

/**
 * Represents a task.
 * Contains description of the task and whether it is completed.
 *
 * @author Zhang Shi Chen
 */
public abstract class Task implements Comparable<Task> {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description Description of the task
     */
    Task(String description) {
        this.description = description;
        isDone = false;
    }

    /** Marks the task as done. */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Checks if description contains the keyword.
     * Also checks if keyword is a type of Task, e.g. Event, and in those cases, return all tasks of that type.
     *
     * @param keyword Keyword to match
     * @return True if the current task contains keyword as part of its description or is the same type as
     *         specified by keyword; else false
     */
    public boolean matchesKeyword(String keyword) {
        return matchesDescription(keyword) || matchesType(keyword);
    }

    private boolean matchesType(String type) {
        switch (type.toLowerCase()) {
        case "todo":
            return this instanceof Todo;
        case "deadline":
            return this instanceof Deadline;
        case "event":
            return this instanceof Event;
        case "done":
            return isDone;
        default:
            return false;
        }
    }

    private boolean matchesDescription(String keyword) {
        return description.contains(keyword);
    }

    abstract LocalDateTime getDateTime();

    /**
     * Formats task in the form of: [ ] description
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Compares the current task with another task.
     * Since todo does not have a time, it will always be behind.
     * This method compares the date and time of deadline vs date and start time of event.
     *
     * @return An int value: 0 if the 2 tasks are same timing;
     *         < 0 if the current task occurs earlier than the other task;
     *         > 0 if the current task occurs later than the other task.
     */
    @Override
    public int compareTo(Task task) {
        return this.getDateTime().compareTo(task.getDateTime());
    }
}
