/**
 * ToDo class represents tasks without any date/time attached to it e.g., visit new theme park.
 *
 * @author Chng Zi Hao
 */
public class ToDo extends Task{
    /**
     * Constructor for Todo.
     *
     * @param description Todo description.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
