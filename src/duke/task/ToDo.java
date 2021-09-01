package duke.task;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    protected static ToDo stringToToDo(String s) {
        return new ToDo(s.substring(12));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String populateSaveData() {
        return "T | " + (this.isDone ? 1 : 0) + " | " + this.description;
    }
}
