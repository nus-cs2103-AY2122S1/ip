package duke;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskInfo() {
        return "T" + "|" + super.getZeroOrOne() + "|" + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
