package duke.task;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline is a task that has a description, date, and optionally a time.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class Deadline extends Task {

    public Deadline(String description, String date, String time) {
        super(description, date, time);
    }


    @Override
    public String getFormattedDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateFormatterOutput = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate date = LocalDate.parse(this.date, dateFormatter);
        return date.format(dateFormatterOutput).toString();
    }


    @Override
    public String getFormattedTime() {
        if (!this.time.isEmpty()) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("k:mm");
            DateTimeFormatter timeFormatterOutput = DateTimeFormatter.ofPattern("h.mma");
            LocalTime time = LocalTime.parse(this.time, timeFormatter);
            return time.format(timeFormatterOutput).toString();
        } else {
            return "";
        }
    }


    @Override
    public String getReadableString() {
        String binaryStatus = this.isDone ? "1" : "0";
        if (this.time.isEmpty()) {
            return "D | " + binaryStatus + " | " + this.description + " | " + this.date + "\n";
        } else {
            return "D | " + binaryStatus + " | " + this.description + " | " + this.date + " " + this.time + "\n";
        }

    }

    @Override
    public String toString() {
        if (this.time.isEmpty()) {
            return "[D]" + super.toString() + " (by: " + this.getFormattedDate() + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + this.getFormattedDate() + " " + this.getFormattedTime() + ")";
        }
    }
}
