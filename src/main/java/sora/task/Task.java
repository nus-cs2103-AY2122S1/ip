package sora.task;

/**
 * Represents a task.
 * Contains description of the task and whether it is completed.
 *
 * @author Zhang Shi Chen
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Checks if description contains the keyword. Also checks if keyword is a type of Task, e.g. Event, and
     * in those cases, return all Task of type Event.
     *
     * @param keyword keyword to match
     */
    public boolean match(String keyword) {
        return matchDescription(keyword) || matchType(keyword);
    }

    private boolean matchType(String type) {
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

    private boolean matchDescription(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
