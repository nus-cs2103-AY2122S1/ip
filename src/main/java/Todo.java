public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public Todo markDone() {
        super.markDone();
        return this;
    }

    @Override
    public String formatChnage() {
        return super.formatChnage();
    }

    @Override
    public String toString() {
        return "[T]"  + super.toString();
    }
}
