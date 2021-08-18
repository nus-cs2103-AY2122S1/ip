public class ToDo extends Task {

    /***
     * Constructor to create a task to do.
     *
     * @param name The name of the task to do.
     */
    public ToDo(String name) {
        super(name);
    }

    /***
     * Returns the string representation of the task to do.
     *
     * @return The name of the task to do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}