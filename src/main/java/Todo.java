public class Todo extends Task {
    public Todo(String description) throws AisuException {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[To-do] %s %s", this.getStatusIcon(), this.description);
    }

}
