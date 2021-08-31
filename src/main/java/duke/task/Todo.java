package duke.task;


/**
 * Todo class to represent a class without a deadline.
 */
public class Todo extends Task {

    protected String by;

    /**
     * Constructor method of Todo.
     *
     * @param description Description of a Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Another constructor method of Todo.
     *
     * @param isDone Done status of a Todo.
     * @param description Description of a Todo.
     */
    public Todo(String isDone, String description) {
        super(description, isDone.equals("1"));
    }

    

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}
