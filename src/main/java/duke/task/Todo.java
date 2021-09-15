package duke.task;

/**
 * Represents a task to be done
 */
public class Todo extends Task {

    /**
     * Todo constructor.
     *
     * @param description Description of task to be done.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Todo)) {
            return false;
        }

        Todo c = (Todo) o;

        return c.description.equals(description);
    }
}