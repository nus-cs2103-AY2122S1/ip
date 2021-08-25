package duke;

public class Deadline extends Task {
    private String deadline;
    public Deadline(String taskContent) {
        super(taskContent.split(" /by ")[0], "D");
        this.deadline = Parser.parseTiming(taskContent.split(" /by ")[1]);
    }
    public Deadline(String taskContent, String deadline) {
        super(taskContent, "D");
        this.deadline = deadline;
    }
    @Override
    public String getTiming() {
        return this.deadline;
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
