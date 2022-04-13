package duke;

public class Todo extends Task {

    /**
     * Todo constructor
     *
     * @param description
     * @param isDone
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overrides toString method
     *
     * @return task description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
