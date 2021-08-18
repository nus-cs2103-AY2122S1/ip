public class Todo extends Task {
    public Todo(String description) throws AisuException {
        super(description);
    }

    @Override
    public String ParseData() {
        return "T;;" + (this.isDone ? "1" : "0") + ";;" + this.description;
    }

    @Override
    public String toString() {
        return String.format("[To-do] %s %s", this.getStatusIcon(), this.description);
    }

}
