package duke;

/**
 * Represents a task, deadline or event
 */
abstract class Task {
    String description;
    Boolean isDone = false;

    void setDone() {
        isDone = true;
    }
}
