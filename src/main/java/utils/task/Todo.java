package utils.task;

/**
 * The Deadline class encapsulates a task without any date/time attached to it.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskFileString(String delimiter, String done, String notDone) {
        return "T" + delimiter + (this.isDone ? done : notDone) + delimiter + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}