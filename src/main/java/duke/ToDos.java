package duke;

/**
 * This class simulates a unique type of Task which only has description
 * but no designated deadline.
 */
public class ToDos extends Task {

    /**
     * Constructor of a ToDos object.
     * @param description description of the ToDos object.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns description of the current ToDos object.
     * @return description of the current ToDos object.
     */
    @Override
    public String getDescription() {
        return super.getDescription().replace("todo ", "");
    }

    /**
     * Marks the current ToDos object as done.
     */
    @Override
    public void markedDone() {
        super.markedDone();
    }

    /**
     * Returns the status icon of the current ToDos object.
     * @return the status icon of the current ToDos object.
     */
    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    /**
     * Returns the current ToDos's description and completion status.
     * @return the current ToDos's description and completion status.
     */
    @Override
    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
