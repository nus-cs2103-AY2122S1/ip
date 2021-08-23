public class Todo extends Task{
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getData() {
        return "T | " + super.getData();
    }
}
