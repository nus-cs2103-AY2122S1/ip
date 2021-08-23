public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
    }

    public Todo(String isDone, String description) {
        super(description, isDone.equals("1"));
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}
