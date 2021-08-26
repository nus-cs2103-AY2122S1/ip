package duke.task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public String convertToSaveFormat() {
        return "T|" + super.convertToSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
