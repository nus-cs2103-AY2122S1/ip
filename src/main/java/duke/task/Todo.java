package duke.task;

/**
 * Todo class under task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * String representation of a todo_item.
     *
     * @return String to be displayed.
     */
    @Override
    public String toString() {
        return ("T " + super.toString());
    }

    /**
     * String format of a todo_item that will be stored to the file.
     *
     * @return String to be stored.
     */
    @Override
    public String toStoredString() {
        return ("T " + super.toString());
    }


}
