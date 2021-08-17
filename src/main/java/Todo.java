/**
 * A type of task that just keeps track of the description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns To do's String form.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
