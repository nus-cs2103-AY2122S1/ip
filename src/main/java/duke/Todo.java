package duke;

public class Todo extends Task {
    protected String by;

    /**
     * Initializes a Todo object
     * @param description
     */
    public Todo(String description) {
        super(description);
        this.label = "T";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
