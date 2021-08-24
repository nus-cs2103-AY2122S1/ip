package tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStringForFile() {
        return "T - " + super.toStringForFile();
    }

    /**
     *
     * @return the String representation of a Deadline
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
