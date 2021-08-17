public class Todo extends Task{
    /**
     * Constructor for Todo
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation of Todo
     * @return string representation of Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
