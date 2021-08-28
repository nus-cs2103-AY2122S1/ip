package duke.task;

import java.time.LocalDate;

/**
 * ToDo is a duke.task that doesnt have any dates tied to it.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo duke.task.
     *
     * @param description the description
     */
    public ToDo(boolean isDone, String description) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the ToDo duke.task.
     *
     * @return the string representation of the ToDO duke.task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns if the duke.task date is equal to the date provided.
     *
     * @param date the date provided
     * @return true if they are both equal
     */
    @Override
    public boolean onDate(LocalDate date) {
        return false;
    }
}
