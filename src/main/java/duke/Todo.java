package duke;

public class Todo extends Task {

    /**
     * Todo constructor
     *
     * @param description
     * @param done
     */
    public Todo(String description, boolean done) {
        super(description, done);
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
