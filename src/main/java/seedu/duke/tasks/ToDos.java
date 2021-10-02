package seedu.duke.tasks;

import java.util.ArrayList;

public class ToDos extends Task {
    /**
     * Constructor which inherits from the super class. Default having the isDone
     * parameter to be false.
     * 
     * @param description is the description of the {@code ToDos} task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Second Constructor which takes in additional boolean, to be able to set the
     * initial boolean status.
     * 
     * @param description is the description of the {@code ToDos} task.
     * @param isDone      determine whether the {@code ToDos} task is completed or
     *                    not.
     */
    public ToDos(String description, boolean isDone, ArrayList<String> tags) {
        super(description, isDone, tags);
    }

    /**
     * Gets the symbol of this {@code ToDos} object. The symbol for {@code ToDos}
     * object is "T".
     * 
     * @return "T"
     */
    @Override
    public String getSymbol() {
        return "T";
    }

    /**
     * Marks the current {@code ToDos} as done.
     * 
     * @return a new {@code ToDos} object with the same description, but setting
     *         {@code isDone} property to be true.
     */
    @Override
    public ToDos markAsDone() {
        return new ToDos(this.getDescription(), true, this.getTags());
    }

    /**
     * Describes the current {@code ToDos} object.
     * 
     * @return a description of the current {@code ToDos} object.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
