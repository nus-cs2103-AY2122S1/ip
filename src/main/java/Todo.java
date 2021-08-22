/**
 * A subclass of Task of todo type.
 */
public class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    Todo(String name, boolean isDone) {
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
