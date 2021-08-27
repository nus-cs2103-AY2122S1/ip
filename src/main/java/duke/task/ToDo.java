package duke.task;
public class ToDo extends Task {
    public ToDo(String input) {
        super(input);
    }

    @Override
    public String formatTask() {
        return "T" + " | " + super.formatTask();
    }

    @Override
    public  String toString() {
        return "[T] " + super.toString();
    }
}
