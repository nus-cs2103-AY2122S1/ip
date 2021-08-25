package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a comma separated string representation of the Todo's data
     * for data storage.
     *
     * @return String that represents the Todo's data
     */

    @Override
    public String getData() {
        return String.format("T,%s", super.getData());
    }

    /**
     * Returns the string representation of a Todo.
     *
     * @return String representing a todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
