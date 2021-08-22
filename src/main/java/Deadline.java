public class Deadline extends Task{
    private String deadline;
    public Deadline(String taskContent) {
        super(taskContent.split(" /by ")[0]);
        this.deadline = formatTiming(taskContent.split(" /by ")[1]);
    }
    @Override
    public String toString() {
        if(super.isCompleted()) {
            return "[D][X] " + super.getTaskContent() + " " + "(by: " + this.deadline + ")";
        }else {
            return "[D][ ] " + super.getTaskContent() + " " + "(by: " + this.deadline + ")";
        }
    }
}
