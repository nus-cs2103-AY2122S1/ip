public class Todo extends Task{

    public Todo(String task) {
        super(task);
    }

    @Override
    public String getDescription() {
        return "[T] " + super.getDescription();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
