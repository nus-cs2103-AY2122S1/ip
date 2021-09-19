package Duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String addToFile() {
        return "T | 0 | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}