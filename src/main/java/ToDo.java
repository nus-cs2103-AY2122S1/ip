/**
 * ToDo class used to represent a task that has neither a start nor end date.
 * Contains method that
 * (i) overrides the Parent toString method to display the task type,
 * as well as status and description.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overriding toString method to display the relevant information
     *
     * @return String type object that includes the task type and parent
     * toString() method.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
