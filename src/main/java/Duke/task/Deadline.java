package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private String by;
    private LocalDate date;
    private int time;

    public Deadline(String details, String by, String date, int time) {
        super(details);
        this.by = by;
        this.date = date == null
                ? null
                : LocalDate.parse(date);
        this.time = time;
    }

    public String getBy() {
        return by;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public int getTime() {
        return time;
    }

    private String twelveHrTime() {
        String timeStr = "";
        if (time == 1200) {
            timeStr = "12:00PM";
        } else if (time == 0) {
            timeStr = "12:00AM";
        } else {
            String min = time % 100 >= 10
                    ? "" + (time % 100)
                    : "0" + (time % 100);
            if (time > 1159) {
                timeStr = timeStr + (((time / 100) - 12) + ":" + min + "PM");
            } else {
                timeStr = timeStr + ((time / 100) + ":" + min + "AM");
            }
        }
        return timeStr;
    }

    @Override
    public String toString() {
        if (date == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ' ' + twelveHrTime() + ")";
        }
    }
}
