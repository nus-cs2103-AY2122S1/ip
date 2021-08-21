import java.time.LocalDate;

public class Event extends Task {

    public Event() {}
    public Event(String desc) {
        super(desc);
    }
    public Event(String desc, String time) throws DukeException {
        super(desc);
        addTime(time);
    }
    
    public Event(String desc, String time, boolean done) {
        super(desc, done);
        // this.time = time;
        addTime(time);
    }

    public void addTime(String time) {
        super.addTime(time);
    }

    public String toDB() {
        // return String.format("E | %d | %s | %s", super.done ? 1 : 0, super.desc, time);
        return String.format("E | %d | %s | %s | %s", super.done ? 1 : 0, super.desc, dateTime.format(Task.formatter), 
                details);
    }

    @Override
    public String toString() {
        String timeSeq = formatTime();
        return "[E]" + super.toString() + (timeSeq != null ? String.format(" (at: %s)", timeSeq) : "");
    }
}
