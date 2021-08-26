import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;

    public Deadline(String description, String deadline, boolean completed) {
        super(description, completed);
        String date = deadline.split(" ")[0];
        String time = deadline.split(" ")[1];
        try {
            this.date = LocalDate.parse(date);
            this.time = LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(), date.toString(), time.toString());
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDeadline() {
        return this.date.toString() + this.time.toString();
    }
}
