public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String listEntry() {
        return "[T]" + super.listEntry();
    }

    @Override
    public String databaseEntry() {
        return "T" + super.databaseEntry();
    }
}
