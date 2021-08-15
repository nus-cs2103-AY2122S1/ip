public class Todo extends Task {
    public Todo(String taskDetails) {
        super(taskDetails);
    }
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
