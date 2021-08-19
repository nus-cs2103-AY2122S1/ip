/**
 * This class represents the ToDo event.
 *
 * @author Nigel Tan
 */
public class ToDo extends Task {
    /**
     * Constructor
     *
     * @param description the name of the task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

