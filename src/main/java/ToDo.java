/**
 * This is a ToDo class that extends Task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String fullCommand() {
        return "todo " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
