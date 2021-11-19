package duke;

/**
 * duke.ToDo represents the ToDo tasks in Duke
 */
public class ToDo extends Task {

    /**
     * Constructor to create a new ToDo
     *
     * @param description the description of the ToDo
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
