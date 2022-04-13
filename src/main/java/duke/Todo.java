package duke;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, String isDone) {
        super(name, isDone.equals("1"));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the To do item as a string for storage.
     * @return the string for storage.
     */
    @Override
    public String toSaveString() {
        return "duke.Todo~" + super.toSaveString();
    }
}