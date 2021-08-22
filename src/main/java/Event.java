public class Event extends Task{
    private String deadline;
    public Event(String taskContent) {
        super(taskContent.split(" /at ")[0]);
        this.deadline = formatTiming(taskContent.split(" /at ")[1]);
    }
    @Override
    public String toString() {
        if(super.isCompleted()) {
            return "[E][X] " + super.getTaskContent() + " " + "(at: " + this.deadline + ")";
        }else {
            return "[E][ ] " + super.getTaskContent() + " " + "(at: " + this.deadline + ")";
        }
    }
}
