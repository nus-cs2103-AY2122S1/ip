public class ToDo extends Task {
    /**
     * Constructor of the class `ToDo`.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
