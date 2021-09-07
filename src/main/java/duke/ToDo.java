package duke;

public class ToDo extends Task{

    /**
     * Constructor for a ToDo object.
     *
     * @param taskContent Content to be stored in the ToDo object.
     */
    public ToDo(String taskContent) {
        super(taskContent, "T");
        assert this.getType().equals("T") : "Wrong type";
    }

    /**
     * Returns the string representation of this ToDo object.
     *
     * @return String representation of ToDo object.
     */
    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[T][X] " + super.getTaskContent();
        } else {
            return "[T][ ] " + super.getTaskContent();
        }
    }
}
