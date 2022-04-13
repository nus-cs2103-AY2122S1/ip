package task;

/**
 * Todo is a subclass of a Task.
 * It has the same behaviour of a task except printing out the task.Task type when using
 * ToString method call.
 */

public class Todo extends Task {

    /**
     * Constructs a Todo object.
     *
     * @param description the task description.
     */
    public Todo(String description, String notes, boolean completed) {
        super(description, notes, completed);
    }

    private String showNotesIfAvailable() {
        if (this.notes.isEmpty()) {
            return "";
        } else {
            return "--" + this.notes + "\n";
        }
    }

    /**
     * Returns the string representation of the Todo object.
     *
     * @return the full string representation of Todo Object.
     */
    @Override
    public String toString() {
        return String.format("[T]%s\n%s", super.toString(), showNotesIfAvailable());
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String getDeadline() {
        return "";
    }
}
