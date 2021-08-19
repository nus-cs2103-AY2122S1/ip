/**
 * A To-do kind of Task.
 * @author Thomas Hogben
 */
public class ToDo extends Task {

    /**
     * @param description The description of the Task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
