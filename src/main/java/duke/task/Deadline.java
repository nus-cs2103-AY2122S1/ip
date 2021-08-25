package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    /**
     * Class constructor for Deadline Class specifying the description and the deadline 'by'
     */
    public Deadline(String description, String by) {
        super(description);

        String[] dateTime = by.split(" ");

        String rawDate = dateTime[0];
        String[] dayMonthYear = rawDate.split("/");
        LocalDate date = LocalDate.of(Integer.parseInt(dayMonthYear[2]), Integer.parseInt(dayMonthYear[1]), Integer.parseInt(dayMonthYear[0]));
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        if (dateTime.length > 1) {
            String rawTime = dateTime[1];
            String processedRawTime = rawTime.substring(0, 2) + ":" + rawTime.substring(2);
            LocalTime time = LocalTime.parse(processedRawTime);
            String formattedTime = time.format(DateTimeFormatter.ofPattern("h a"));

            this.by = formattedDate + ", " + formattedTime;
        }
    }

    /**
     * Class constructor for Deadline Class specifying the description, isDone and the deadline 'by'
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Return the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Return the string format for data source
     *
     * @return           string format of task for data source
     */
    @Override
    public String toData() {
        return "D" + super.toData() + " | " + this.by;
    }
}       