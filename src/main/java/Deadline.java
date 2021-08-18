public class Deadline extends Task{
    protected String deadline;
    Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString(){
        return "[D]" + "[" + this.getStatusIcon() + "]" + " " + this.description + "(" + deadline + ")";
    }
}
