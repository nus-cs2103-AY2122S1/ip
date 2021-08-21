import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public abstract class Task {
    protected String desc;
    protected boolean done;
    protected String details = null;
    protected LocalDateTime dateTime = null;

    public Task() {}

    public Task(String desc) {
        this.desc = desc;
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
        String dt = dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        if (details == null) return dt;
        return String.format("%s, %s", dt, details);
    }
    
    protected void addTime(String time) {
        String[] args = time.split(" / ");
        if (args.length == 0) return;
        try {
            dateTime = LocalDateTime.parse(args[0]);
            if (args.length > 1) details = args[1];
        } catch (DateTimeException e) {
            details = time;
        }
    }
    
    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + desc;
    }

}
