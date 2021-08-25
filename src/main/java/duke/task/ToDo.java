package duke.task;


public class ToDo extends Task {

    public ToDo(String detail) {
        super(detail, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
