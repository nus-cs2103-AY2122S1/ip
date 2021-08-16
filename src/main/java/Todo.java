public class Todo extends Task {
    protected String by;

    /**
     * A constructor to create a Todo object
     *
     * @param description A description about the Todo object
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A method to get the string representation of a Todo object
     *
     * @return The string representation of a Todo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
