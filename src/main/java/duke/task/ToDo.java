package duke.task;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.getName();
    }

    public String logo() {
        return "[T]";
    }
}
