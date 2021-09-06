package bobcat.model.task;

/**
 * A Task which represents a task with no starting time or deadline
 */
public class ToDo extends Task {

    public ToDo(String entry, boolean status) {
        super(entry, status);
    }

    public ToDo(String entry) {
        super(entry);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
