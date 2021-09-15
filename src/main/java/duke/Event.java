package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected LocalDate date;
    protected String afterDate;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        String[] arr = at.split(" ", 2);
        this.afterDate = arr.length == 2 ? arr[1] : "";
        this.date = LocalDate.parse(arr[0]);
    }

    @Override
    public String toWrite() {
        int marked = this.isDone ? 1 : 0;
        return String.format("E|%d|%s|%s\n", marked, this.description, this.at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s %s)", super.toString(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.afterDate);
    }
}