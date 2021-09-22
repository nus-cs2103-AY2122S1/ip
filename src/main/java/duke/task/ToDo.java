package duke.task;

/**
 * Class that handles ToDo which is a type of task.
 */
public class ToDo extends Task {

    /**
     * Constructor to initialize Todo.
     *
     * @param detail Description of Todo.
     */
    public ToDo(String detail) {
        super(detail, "[T]");
    }
}
