/**
 * A Task without any date/time attached to it.
 *
 * @author Lethicia
 */
public class ToDo extends Task{

    /**
     * Constructor for a ToDo Task.
     *
     * @param description the title or description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
