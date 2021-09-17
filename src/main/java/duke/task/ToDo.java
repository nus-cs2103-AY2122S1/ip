package duke.task;

/**
 * ToDo class represents a Task with no start time or deadline.
 */
public class ToDo extends Task {

    /**
     * Class constructor which receives a description of the Task.
     *
     * @param description Description of the Task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Method which returns the String representation of the Task to be displayed to the user.
     *
     * @return String Representation of the Task to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + super.priorityString();
    }

    /**
     * Method which returns the String representation of the Task to be displayed to the user.
     *
     * @return String Representation of the Task to be displayed to the user.
     */
    @Override
    public String toFile() {
        return "T " + super.toFile();
    }
}
