package duke.task;


public class Todo extends Task {
    /**
     * Constructs a Todo task.
     *
     * @param description
     */
    public Todo(String description, String priority) {
        super(description, priority);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String convertToFileFormat() {
        return String.format("T%s", super.convertToFileFormat());
    }

}
