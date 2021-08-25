import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends TimedTask {
    public Event(String name, String time){
        super(name, time);
    }

    @Override
    public String format(DateTimeFormatter dtformatter){
        return String.format("[E][%s] %s (at: %s)",
                this.isDone ? "X" : " ", name, dtformatter.format(time));
    }

    public Event(String name, String time, boolean isDone){
        super(name, time, isDone);
    }

    @Override
    public String toCsvRow() {
        return String.join(",", "event", name, time.toString(), String.valueOf(isDone));
    }

    @Override
    public String toString(){
        return String.format("[E][%s] %s (at: %s)",
                this.isDone ? "X" : " ", name, time);
    }
}
