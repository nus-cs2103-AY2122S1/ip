public class Deadline extends Task{
    public String ddlTime;
    public Deadline (String task, String ddlTime) {
        super(task);
        this.ddlTime = ddlTime;
    }

    @Override
    public String toString() {
        String finished = " ";
        if (this.done) {
            finished = "X";
        }
        return "[D]" + "[" + finished + "] " + this.taskName + " (by: " + this.ddlTime + ")";
    }
}
