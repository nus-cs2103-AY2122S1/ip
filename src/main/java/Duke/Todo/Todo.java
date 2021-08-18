package Duke.Todo;

public class Todo {
    private String todo;

    public Todo(String todo) {
        this.todo = todo;
    }

    @Override
    public String toString() {
        return this.todo;
    }
}
