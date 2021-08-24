import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private DateTimeFormatter printOut = DateTimeFormatter.ofPattern("MMM dd, E, yyyy");
    protected LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description, Task.Type.D);
        this.deadline = deadline;

    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + printOut.format(deadline) + ")";
    }
}
