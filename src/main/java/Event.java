public class Event extends Task{
    private boolean done;
    private String taskString;
    private String timeSlot;

    public Event(String taskString, String timeSlot) {
        this.taskString = taskString;
        this.done = false;
        this.timeSlot = timeSlot;
    }

    public void markAsDone() {
        this.done = true;
    }

    public String getTaskString() {
        if(done) {
            return "[E][X] " + this.taskString + " (at: " + this.timeSlot + ")";
        } else {
            return "[E][ ] " + this.taskString + " (at: " + this.timeSlot + ")";
        }
    }
}

