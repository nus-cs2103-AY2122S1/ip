package duke.tasktypes;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description, "T");
    }

    @Override
    public String saveToHardDisk() {
        return "T/" + this.getBooleanStatus() + "/" + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
