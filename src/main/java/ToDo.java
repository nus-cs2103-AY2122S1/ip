package main.java;

/**
 * ToDo is a task that doesnt have any dates tied to it.
 *
 * @author Zhen Xuan (Tutorial Group 04)
 * @version CS2103T AY21/22 S2
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo task.
     *
     * @param description the description
     */
    public ToDo(String description) {
        super(description);
        super.printInitTask();
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return the string representation of the ToDO task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}