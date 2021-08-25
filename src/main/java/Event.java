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

    @Override
    public String toString(){
        return String.format("[E][%s] %s (at: %s)",
                this.isDone ? "X" : " ", name, time);
    }
}
