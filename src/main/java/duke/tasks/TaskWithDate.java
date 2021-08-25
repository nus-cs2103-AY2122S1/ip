package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Is a special task with dates. Deadline and Event will inherit from this.
 */
public class TaskWithDate extends Task{
    public final LocalDate date;
    
    public TaskWithDate(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    public void printDate() {
        System.out.println(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
