import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a
 * deadline task with a specified deadline.
 *
 * @author Pawandeep Singh
 * @version Level-4
 *
 * */
public class Deadline extends Task{
    private LocalDateTime deadline;

    public Deadline(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by:"+ this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma"))  +")";
    }
}
