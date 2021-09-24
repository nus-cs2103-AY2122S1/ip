package duke;

public class ToDo extends Task {
    public ToDo(String todo) {
        super(todo, "T");
    }

    /**
     * Formats details of todo task into one string.
     *
     * @return String representation of a todo task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getTaskSymbol(), super.toString());
    }
}
