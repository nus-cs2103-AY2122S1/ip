package nyx;

public class ToDo extends Task {
    public ToDo(String content, boolean isDone) {
        super(content, isDone);
    }

    public ToDo(String content) {
        this(content, false);
    }

    @Override
    public String dataFormat() {
        return String.format("T, %d, %s\n", isDoneInt(), getContent());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}