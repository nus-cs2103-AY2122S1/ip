package duke.tasktypes;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns in a format to save to file.
     */
    @Override
    public String saveToHardDisk() {
        return "T/" + this.getBooleanStatus() + "/" + this.getDescription();
    }

    /**
     * Returns string format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
