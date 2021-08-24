import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task{
    private LocalDateTime deadline;
    private final static String symbol = "[D]";

    public Deadline(String action, String deadline){
        super(action);
        this.deadline = Task.parseDate(deadline);
    }

    public String toString(){
        return String.format("%s%s (by: %s)", symbol, super.toString(),
                this.deadline.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)));
    }
}
