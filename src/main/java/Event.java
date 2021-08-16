public class Event extends Task {

    String time;

    Event(String desc, String time) {
        super(desc);
        this.time = time;
    }

    @Override
    public String toString() {
       return "[E]" + this.completionStatus() + desc + " (at: " + time + ")";
    }
}
