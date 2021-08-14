public class ToDo extends Task {
    /**
     * Constructor for the ToDo task.
     * @param name Name of the Task.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
