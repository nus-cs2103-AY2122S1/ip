package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        char done = '0';
        if (super.isDone) {
            done = '1';
        }
        return "T | " + done + " | " + getDescription();
    }

    @Override
    public String toString() {
        return " [T]" + super.toString();
    }
}
