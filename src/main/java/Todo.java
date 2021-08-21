public class Todo extends Task{

    public Todo(String description, boolean completionStatus) {
        super(description, completionStatus);
    }

    @Override
    public String typeIcon() {
        return "[T]";
    }
}
