public class Todo extends Task {
    public Todo(String label) {
        super(label);
    }

    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
