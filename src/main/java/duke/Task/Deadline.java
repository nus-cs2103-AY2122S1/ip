package duke.Task;
import java.time.LocalDate;

public class Deadline extends Task{
    public LocalDate date;
    public Deadline(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public boolean contains(String keyword) {
        return super.contains(keyword) || this.date.toString().contains(keyword);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.date + ")";
    }
}
