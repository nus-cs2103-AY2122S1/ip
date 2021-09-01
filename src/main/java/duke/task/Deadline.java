package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Arrays;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] arr = by.split(" ");
        String[] arr1 = arr[0].split("\\/");
        this.date = LocalDate.parse(String.format("%s-%s-%s", arr1[2], arr1[1].length() == 1 ?
                "0" + arr1[1] : arr1[1], arr1[0].length() == 1 ? "0" + arr1[0] : arr1[0]));
        this.time = LocalTime.parse(arr[1].substring(0, 2) + ":" + arr[1].substring(2));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                this.time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }

    @Override
    public String fileFormat() {
        return String.format("D%s | %s", super.fileFormat(), this.by);
    }
}
