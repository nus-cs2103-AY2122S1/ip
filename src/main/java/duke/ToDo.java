package duke;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns the string representation of the ToDo object.
     * @return the string representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * This returns the string format the task is represented in the duke file.
     * @return the string representing a ToDo in the duke file
     */
    @Override
    public String print() {
        return String.format("T,%d,%s",isCompleted() ? 1 : 0, this.getName());
    }
}
