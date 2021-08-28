package duke.command;

// import duke packages
import duke.task.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, 'T');
    }

    @Override
    public String toString() {
        return "[" + this.getCat() + "]" + super.toString();
    }
}