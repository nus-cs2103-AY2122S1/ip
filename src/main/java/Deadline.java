import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;

    DateTimeFormatter dayOutputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    DateTimeFormatter timeOutputFormatter = DateTimeFormatter.ofPattern("ha");

    DateTimeFormatter dayInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeInputFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Deadline(String description, String deadline, boolean completed) throws InvalidDateFormat {
        super(description, completed);
        String date = deadline.split(" ")[0];
        String time = deadline.split(" ")[1];
        try {
            this.date = LocalDate.parse(date,dayInputFormatter);
            this.time = LocalTime.parse(time,timeInputFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormat();
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(),
                date.format(dayOutputFormatter), time.format(timeOutputFormatter));
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDeadline() {
        return this.date.toString() + " " + this.time.toString();
    }
}
