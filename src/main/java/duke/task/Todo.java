package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String formatSave() {
        return "T |" + ((super.isDone) ? " 1 | " : " 0 | ") + super.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}