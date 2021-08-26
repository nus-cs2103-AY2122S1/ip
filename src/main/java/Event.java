import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private LocalDate date;
    private LocalTime time;
    DateTimeFormatter dayOutputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    DateTimeFormatter timeOutputFormatter = DateTimeFormatter.ofPattern("ha");

    DateTimeFormatter dayInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter timeInputFormatter = DateTimeFormatter.ofPattern("HHmm");

    public Event(String description, String deadline, boolean completed) throws InvalidDateFormat {
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
        return String.format("[E]%s (at: %s %s)", super.toString(),
                date.format(dayOutputFormatter), time.format(timeOutputFormatter));
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDeadline() {
        return this.date.toString() + this.time.toString();
    }
}
