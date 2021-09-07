package duke.task;


public class Todo extends Task {
    /**
     * Constructs a Todo task.
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String fileFormat() {
        return String.format("T%s", super.fileFormat());
    }

}
