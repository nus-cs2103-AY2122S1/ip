package duke.task;

/**
 * A ToDo task.
 */
public class ToDoTask extends Task {

    public ToDoTask(String name) {
        super(name);
    }

    public ToDoTask(String name, boolean completed) {
        super(name, completed);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
