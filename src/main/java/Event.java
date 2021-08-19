public class Event extends Task{
    private String deadline;
    public Event(String taskContent) {
        super(taskContent.split(" /at ")[0]);
        this.deadline = "(at: " + taskContent.split(" /at ")[1] + ")";
    }
    @Override
    public String toString() {
        if(super.isCompleted()) {
            return "[E][X] " + super.getTaskContent() + " " + this.deadline;
        }else {
            return "[E][ ] " + super.getTaskContent() + " " + this.deadline;
        }
    }
}
