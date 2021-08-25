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

    @Override
    public String toString(){
        return String.format("[D][%s] %s (by: %s)",
                this.isDone ? "X" : " ", name, time);
    }
}
