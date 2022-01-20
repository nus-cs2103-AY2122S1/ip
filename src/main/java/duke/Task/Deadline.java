package duke.Task;
import java.time.LocalDate;

/**
 * Type of task that has description and deadline
 */
public class Deadline extends Task{
    private LocalDate dateDeadline;

    public Deadline(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.dateDeadline = date;
    }

    @Override
    public boolean contains(String keyword) {
        return super.contains(keyword) || this.dateDeadline.toString().contains(keyword);
    }

    @Override
    public LocalDate getDate(){
        return this.dateDeadline;
    }

    @Override
    public int compareTo(Task o) {
        return this.getDate().compareTo(o.getDate());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dateDeadline + ")";
    }
}
