public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }
    public Todo(String desc, boolean done) {
        super(desc, done);
    }

    public String toDB() {
        return String.format("T | %d | %s ", super.done ? 1 : 0, super.desc);
    }

    @Override
    public String toString() {
        return "[T]" + (done ? "[X] " : "[ ] ") + desc;
    }
}
