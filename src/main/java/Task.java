import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected String desc;
    protected boolean done;
    protected String details = new String();
    protected LocalDateTime dateTime = LocalDateTime.now();
    public final static DukeException FORMAT_EXCEPTION = 
            new DukeException("I don't understand this entry, enter 'help' to learn the correct formatting!");

    public Task() {}

    public Task(String desc) {
            this.desc = desc;
    }
    
    public Task(String desc, boolean done) {
        this.desc = desc;
        this.done = done;
    }

    public void addDesc(String desc) {
        this.desc = desc;
    }

    public void markComplete() {
        done = true;
    }
    
    protected String formatTime() {
        if (details == null && dateTime == null) return null;
        if (dateTime == null) return details;
        String dt = String.format("%s on %s",
                dateTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                dateTime.format(DateTimeFormatter.ofPattern("dd MMM")));
        if (details == null) return dt;
        return String.format("%s -- %s", dt, details);
    }
    
    abstract void addTime(String time) throws DukeException;
    
    abstract String toDB();

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + desc;
    }

}
