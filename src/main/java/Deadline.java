public class Deadline extends Task{
    protected String deadline;
    Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }


    @Override
    public String toString(){
        return "[D]" + "[" + this.getStatusIcon() + "]" + " " + this.description + " (by: " + deadline + ")";
    }

    @Override
    public String save() {
        return "D | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " +  this.deadline;
    }
}
