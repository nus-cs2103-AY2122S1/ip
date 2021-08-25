package duke;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public char getTaskType() { return 'T'; }

    @Override
    public String getTime() {
        return "";
    }

    @Override
    public String toString() {
        return "  [T]" + super.toString();
    }
}
