package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String saveString() {
        return "T | " + super.saveString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}