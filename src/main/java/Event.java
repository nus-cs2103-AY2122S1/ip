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
    
    public void addTime(String time) {
        super.addTime(time);
    }

    @Override
    public String toString() {
        String timeSeq = formatTime();
        return "[E]" + super.toString() + (timeSeq != null ? String.format(" (at:%s)", timeSeq) : "");
    }
}
