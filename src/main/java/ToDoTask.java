public class ToDoTask extends Task{

    public ToDoTask(String description) {
        super(description);
    }

    public ToDoTask(String isCompleted, String description) {
        super(isCompleted, description);
    }

    public String getType() {
        return "TODO";
    }

    @Override
    public String toString() {
        return String.format("[T]" + super.toString());
    }
}