public class Todo extends Task {
    public Todo(String taskstr) {
        super(taskstr);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

}
