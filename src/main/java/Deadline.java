import java.time.LocalDate;

public class Deadline extends Task{
    private String date;
    Deadline(String description, boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.date + ")";
    }
}
