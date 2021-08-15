public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[To-do] %s %s", this.getStatusIcon(), this.description);
    }

}
