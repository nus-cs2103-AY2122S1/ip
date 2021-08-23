public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }
    private String tag = "T";

    @Override
    public String getAdditionalInfo() { return "";}

    @Override
    public String getTag() { return tag; }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}