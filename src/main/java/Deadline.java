import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends TimedTask {
    public Deadline(String name, String time){
        super(name, time);
    }

    @Override
    public String format(DateTimeFormatter dtformatter){
        return String.format("[D][%s] %s (by: %s)",
                this.isDone ? "X" : " ", name, dtformatter.format(time));
    }

    public Deadline(String name, String time, boolean isDone){
        super(name, time, isDone);
    }

    @Override
    public String toCsvRow() {
        return String.join(",", "deadline", name, time.toString(), String.valueOf(isDone));
    }

    @Override
    public String toString(){
        return String.format("[D][%s] %s (by: %s)",
                this.isDone ? "X" : " ", name, time);
    }
}
