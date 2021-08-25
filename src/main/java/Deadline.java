import java.time.LocalDate;

public class Deadline extends Task{
    public LocalDate date;
    Deadline(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.date + ")";
    }
}
