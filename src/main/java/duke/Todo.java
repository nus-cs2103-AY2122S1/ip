package duke;

/**
 * Represents a simple Task.
 */
public class Todo extends Task {
    public Todo(String desc) {
        super(desc.trim());
    }

    public Todo(String desc, boolean isDone) {
        super(desc.trim(), isDone);
    }

    /**
     * Returns the task as a String formatted to be saved.
     *
     * @return The formatted String.
     */
    @Override
    public String saveTask() {
        return "T|" + this.isDone() + "|" + this.getDesc() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + this.statusIcon() + this.getDesc();
    }
}
