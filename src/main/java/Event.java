public class Event extends Task {

    String time;

    Event(String desc, Boolean isDone, String time) {
        super(desc, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
       return "[E]" + this.completionStatus() + desc + " (at: " + time + ")";
    }
}
