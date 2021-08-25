package duke.task;

/**
 * Represents an todo task with a <code>description</code> corresponding to the content.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
