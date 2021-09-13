package duke.tasks;

/**
 * Class that encapsulates a ToDo.
 * A ToDo is a type of task with no associated datetime information.
 */
public class ToDo extends Task {

    /**
     * A Constructor for a ToDo.
     *
     * @param description The String description of the ToDo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * An alternate constructor for a ToDo that may be completed
     *
     * @param description A String description of the ToDo
     * @param isComplete A Boolean representing if the ToDo is complete
     */
    public ToDo(String description, Boolean isComplete) {
        super(description, isComplete);
    }

    /**
     * Returns an easily parsable, String file representation of a ToDo
     * for use in persistent storage.
     *
     * @return An easily parsable String representing the ToDo
     */
    @Override
    public String getFileRepr() {
        return "T" + super.getFileRepr();
    }

    /**
     * Returns a String representation of a ToDo for use in
     * the Duke UI.
     *
     * @return A user-friendly, readable String representing the ToDo
     */
    @Override
    public String toString() {
        if (this.isCompleted()) {
            return ("[T][X] " + this.getDescription());
        } else {
            return ("[T][ ] " + this.getDescription());
        }
    }
}
