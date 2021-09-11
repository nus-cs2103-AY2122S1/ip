package duke;

/** Child class that inherits from the parent 'Task' class
 * and handles the To-do tasks */
public class Todo extends Task {
    /**
     * Constructor for to-do class
     *
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
