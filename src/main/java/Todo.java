public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDescriptionWithStatus() {
        return "[T]" + super.getDescriptionWithStatus();
    }

}
