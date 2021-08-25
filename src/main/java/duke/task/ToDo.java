package duke.task;

public class ToDo extends Task {
    public ToDo(String str) {
        super(str);
    }

    public ToDo(String isDone, String desc) {
        super(isDone, desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveString() {
        return "T|" + super.saveString();
    }
}
