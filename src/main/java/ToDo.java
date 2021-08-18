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
     * @param pos         the position in the list
     */
    public ToDo(String description, int pos) {
        super(description, pos);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

