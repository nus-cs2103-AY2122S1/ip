public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getFileString() {
        int i = this.isDone ? 1 : 0;
        return "T | " + i + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}