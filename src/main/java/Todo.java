public class Todo extends Task{
    public Todo(String description) {
        super(description);
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
