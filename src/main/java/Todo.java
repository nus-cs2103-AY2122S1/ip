public class Todo extends Task {
    /**
     * ToDo constructor.
     *
     * @param description the description
     */
    private Todo(String description) {
        super(description);
    }

    /**
     * Factory ToDo method.
     *
     * @param description the description
     * @return a new ToDo object
     */
    public static Todo of(String description) {
        Todo newTodo = new Todo(description);
        feedback(newTodo.toString());
        return newTodo;
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", isDone ? "[X]" : "[ ]", description);
    }
}
