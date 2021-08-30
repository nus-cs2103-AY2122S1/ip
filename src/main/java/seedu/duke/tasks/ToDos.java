package seedu.duke.tasks;

public class ToDos extends Task {
    /**
     * Constructor which inherits from the super class. Default having the isDone
     * parameter to be false.
     * 
     * @param description is the description of the ToDo task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Second Constructor which takes in additional boolean, to be able to set the
     * initial boolean status.
     * 
     * @param description is the description of the ToDo task.
     * @param isDone      determine whether the task is completed or not.
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Get the symbol of this ToDos object. The symbol for ToDos object is "T".
     * 
     * @return "T"
     */
    @Override
    public String getSymbol() {
        return "T";
    }

    /**
     * Marks the current ToDos as done.
     * 
     * @return a new ToDos object with the same description, but setting isDone
     *         property to be true
     */
    @Override
    public ToDos markAsDone() {
        return new ToDos(this.getDescription(), true);
    }

    /**
     * Describes the current ToDos object.
     * 
     * @return a description of the current ToDos object.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
