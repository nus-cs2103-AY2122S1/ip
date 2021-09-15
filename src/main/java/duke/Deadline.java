package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;
    protected String afterDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] arr = by.split(" ", 2);
        this.afterDate = arr.length == 2 ? arr[1] : "";
        this.date = LocalDate.parse(arr[0]);
    }

    @Override
    public String toWrite() {
        int marked = this.isDone ? 1 : 0;
        return String.format("D|%d|%s|%s\n", marked, this.description, this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.afterDate);
    }
}