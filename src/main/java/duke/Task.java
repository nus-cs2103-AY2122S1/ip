package duke;

/**
 * Represents a task, deadline or event.
 */
abstract class Task {
    String description;
    Boolean isDone = false;

    String getDescription() {
        return description;
    }

    void setDone() {
        isDone = true;
    }
}
