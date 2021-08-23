import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);

        String[] dateTime = by.split(" ");

        String rawDate = dateTime[0];
        String[] dayMonthYear = rawDate.split("/");
        LocalDate date = LocalDate.of(Integer.parseInt(dayMonthYear[2]), Integer.parseInt(dayMonthYear[1]), Integer.parseInt(dayMonthYear[0]));
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        if (dateTime.length > 1) {
            // store date and time
            String rawTime = dateTime[1];
            String processedRawTime = rawTime.substring(0, 2) + ":" + rawTime.substring(2);
            LocalTime time = LocalTime.parse(processedRawTime);
            String formattedTime = time.format(DateTimeFormatter.ofPattern("h a"));

            this.by = formattedDate + ", " + formattedTime;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}       