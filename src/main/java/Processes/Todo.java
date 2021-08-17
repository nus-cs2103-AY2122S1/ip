package Processes;

public class Todo extends Task {

    public Todo(String description) {
        super(description, "");
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.description;
        }
        return "[T][ ] " + this.description;
    }
}
