public class Todo extends Task {
    /**
     * Constructor of the Todo class
     *
     * @param description description of this todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this todo
     *
     * @return string representation of this todo
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}