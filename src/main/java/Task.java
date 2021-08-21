import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected String desc;
    protected boolean done;
    protected String details = null;
    protected LocalDateTime dateTime = null;

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
        String dt = dateTime.format(DateTimeFormatter.ofPattern("dd MMM, HH:mm"));
        if (details == null) return dt;
        return String.format("%s -- %s", dt, details);
    }
    
    protected void addTime(String time) {
        String[] args = time.split(" / ");
        if (args.length == 0) return;
        try {
            dateTime = LocalDateTime.parse(args[0], formatter);
            if (args.length > 1) details = args[1];
        } catch (DateTimeException e) {
            details = time;
        }
    }
    
    public abstract String toDB();

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + desc;
    }

}
