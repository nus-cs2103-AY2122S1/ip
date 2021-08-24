package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalDate date;

    public Event(String input, LocalDate date, LocalTime startTime, LocalTime endTime ) throws DukeException {
        super(input);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
            return String.format("[E]%s(at: %s from: %s)", super.toString(),
                    this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    this.startTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + "-"
                            + this.endTime.format(DateTimeFormatter.ofPattern("hh:mm a")));

    }
}
