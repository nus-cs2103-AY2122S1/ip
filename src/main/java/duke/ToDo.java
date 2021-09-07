package duke;

/**
 * This class simulates a unique type of Task which only has description
 * but no designated deadline.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param description Description of the ToDo object.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns description of the current ToDo object.
     *
     * @return Description of the current ToDo object.
     */
    @Override
    public String getDescription() {
        String description = super.getDescription();
        if (description.startsWith("t ")) {
            description = description.replace("t ", "");
        } else {
            description = description.replace("todo ", "");
        }
        return description;
    }

    /**
     * Marks the current ToDo object as done.
     */
    @Override
    public void markDone() {
        super.markDone();
    }

    /**
     * Returns the status icon of the current ToDo object.
     *
     * @return Status icon of the current ToDo object.
     */
    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    /**
     * Returns the current ToDo's description and completion status.
     *
     * @return Current ToDo's description and completion status.
     */
    @Override
    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
