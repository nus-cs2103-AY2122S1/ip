public class TodoTask extends Task {
    public TodoTask(String content, boolean isDone) {
        super(content, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
