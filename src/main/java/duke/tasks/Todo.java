package duke.tasks;
/**
 * Represent a task with no deadline or date.
 */
public class Todo extends Task{

    public Todo(String description, boolean done) {
        super(description);
        this.isDone = done;
    }

    public Todo(String description) {
        super(description);
    }
    
    @Override
    public String toString() {
        return  "[T]" + super.toString();
    }
}