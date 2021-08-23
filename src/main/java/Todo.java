public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    public Todo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String saveTask() {
        return "T|" + this.isDone() + "|" + this.getDesc() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + this.statusIcon() + this.getDesc();
    }
}
