package duke.task;

public class Todo extends Task {
    public Todo(String label) {
        super(label);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getDate() {
        return "";
    }
}
