package yoyo.task;

/**
 * A subclass of duke.task.Task of todo type.
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String printType() {
        return "[T]";
    }

    @Override
    public String showStatus() {
        return super.showStatus();
    }


}
