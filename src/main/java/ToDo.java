public class ToDo extends Task { //this only needs a description and isDone
    public ToDo(String description) {
        super(description);
    }

    public String getTaskIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "] [" + this.getStatusIcon() + "] " + description;
    }
}
