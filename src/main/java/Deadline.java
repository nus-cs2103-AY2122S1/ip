public class Deadline extends Task{
    private String ddl;

    public Deadline(String taskTitle, String ddl) {
        super(taskTitle);
        this.ddl = ddl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + ddl + ")";
    }
}
