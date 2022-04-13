package lebron.task;

/**
 * This class represents the ToDo event.
 *
 * @author Nigel Tan
 */
public class ToDo extends Task {

    /**
     * Constructor.
     *
     * @param description the name of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Produces the desired format for storing to file.
     *
     * @return the desired string.
     */
    @Override
    public String getStringForFile() {
        return "T | " + super.getDoneValue() + " | " + super.getName();
    }

    /**
     * Overrides the makeCopy() method in Task.
     *
     * @return the copied task.
     */
    @Override
    public ToDo makeCopy() {
        return new ToDo(this.getName());
    }
}

