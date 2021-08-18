public class ToDoTask extends Task {
    /**
     * Constructor for ToDoTask
     *
     * @param description is the string of the description of the given task
     */
    public ToDoTask(String description) {
        super(description, "todo");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}