package duke.task;
import duke.DukeException;

/**
 * Class that encapsulates Todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description Task description.
     * @throws DukeException If user input is invalid.
     */
    public Todo(String description) throws DukeException {
        super(description);
    }

    /**
     * Returns a String representation of the Todo task.
     *
     * @return A String representation of the Todo task.
     */
    @Override
    public String toString() {
        return ("\t[T]" + super.toString());
    }
}
