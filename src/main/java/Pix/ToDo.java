package Pix;

public class ToDo extends Task {
    /**
     * Constructor for the Pix.ToDo task.
     * @param name Name of the Pix.Task.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
