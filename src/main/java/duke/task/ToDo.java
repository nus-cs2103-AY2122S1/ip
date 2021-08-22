package duke.task;

/**
 * This class encapsulates a ToDo.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class ToDo extends Task {
    /**
     * Instantiates a new To do.
     *
     * @param description the description for todos task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String convertToTxt() {
        return String.format("T | %s", super.convertToTxt());
    }

    @Override
    public boolean isSameDateTime(String dateTime) {
        return false;
    }

    /**
     * String representation of Todos task.
     *
     * @return String representation of Todos task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
