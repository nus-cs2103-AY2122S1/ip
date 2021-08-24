package duke.task;

public class Todo extends Task {

    public Todo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toStorageString() {
        String s1 = super.toStorageString();
        String s2 = String.format("T %s", s1);
        return s2;
    }
}
