/**
 * Todo (Task). Can be added to list in Duke.
 *
 * @author Ruth Poh (Lab 10H)
 */
public class Todo extends Task {

    /**
     * Constructor to initialize Todo.
     *
     * @param taskstr Task.
     */
    public Todo(String taskstr) {
        super(taskstr);
    }

    /**
     * Returns string of Deadline (Task).
     * @return string of Deadline.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

}
