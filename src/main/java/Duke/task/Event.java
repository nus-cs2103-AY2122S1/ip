package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String at;
    private LocalDate date;
    private int startTime;
    private int endTime;

    public Event(String details, String at, String date, int startTime, int endTime) {
        super(details);
        this.at = at;
        this.date = date == null
                ? null
                : LocalDate.parse(date);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getAt() {
        return at;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public int getTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    private String twelveHrTime(int time) {
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
            return "[E]" + super.toString() + " (at: " + at + ")";
        } else {
            return "[E]" + super.toString() + " (at: "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ' ' + twelveHrTime(startTime) + " to " + twelveHrTime(endTime) + ")";
        }
    }
}
