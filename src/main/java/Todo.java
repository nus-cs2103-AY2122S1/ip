public class Todo extends Task {
    public Todo(String desc) {
        super(desc, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
