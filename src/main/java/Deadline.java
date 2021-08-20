public class Deadline extends Task {
    private String dueBy;

    public Deadline(String todoName) {
        super(todoName.substring(9, todoName.indexOf("/by ")));
        int start = todoName.indexOf("/by ");
        this.dueBy = todoName.substring(start + 4);
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + "(by: "
                + this.dueBy
                + ")";
    }
}
