package duke.task;
public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description);
    }

    public ToDoTask(String isCompleted, String description) {
        super(isCompleted, description);
    }

    public String writeToFile() {
        return String.format("TODO | %s | %s\n", getIsCompleted(), getDescription());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
