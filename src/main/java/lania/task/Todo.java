package lania.task;

public class Todo extends Task {

    /**
     * Constructor for lania.task.Todo. Takes in a String description.
     * Initialises description of the todo.
     *
     * @param description The name of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    public String getStringFormat() {
        return "T|" + this.getStatusIcon() + "|" + this.description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
