public class Todo extends Task {
    
    public Todo(String description) {
        super(description);
    }

    public String save() {
        return String.format("T | %s", super.save());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
