public class Todo extends Task {

    public Todo(String description, boolean done) { super(description, done); }

    @Override
    public String toFileData() {
        return "T," + super.toFileData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
