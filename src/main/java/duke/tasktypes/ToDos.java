package duke.tasktypes;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description, "T");
    }

    @Override
    public String hardDiskSave() {
        return "T/" + this.getBooleanStatus() + "/" + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
