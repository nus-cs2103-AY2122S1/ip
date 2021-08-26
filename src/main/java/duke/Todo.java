package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String num, String description) {
        super(description);
        this.isDone = !num.equals("0");
    }

    @Override
    public String getFileString() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}