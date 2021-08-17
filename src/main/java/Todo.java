/**
 * A subclass of Task of todo type.
 */
public class Todo extends Task {
    Todo(String name) {
        super(name);
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
