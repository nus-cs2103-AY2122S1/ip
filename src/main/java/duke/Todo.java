package duke;

public class Todo extends Task {
    Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    public String save() {
        return "T | " + (this.isDone ? 1 : 0) + " | " + this.description;
    }
}
