package duke.task;

public class Todo extends Task {

    public Todo(String task) {
        super(task, false);
    }

    public Todo(String task, String isDone) {
        super(task, isDone.equals("1"));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "T|" + super.toSaveString();
    }
}
