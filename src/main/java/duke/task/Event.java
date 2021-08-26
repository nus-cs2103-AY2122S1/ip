package duke.task;

import duke.util.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public Event(String taskName, LocalDate date,
                 LocalTime startTime, LocalTime endTime) throws DukeException {
        super(taskName);
        // If start time is greater than or equal to end time, throw exception
        if (startTime.compareTo(endTime) >= 0) {
            throw new DukeException("Start time of event cannot be later or equal to end time.");
        }
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String taskName, boolean isDone, LocalDate date,
                 LocalTime startTime, LocalTime endTime) throws DukeException {
        super(taskName, isDone);
        // If start time is greater than or equal to end time, throw exception
        if (startTime.compareTo(endTime) >= 0) {
            throw new DukeException("Start time of event cannot be later or equal to end time.");
        }
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Task markAsDone() {
        return new Event(this.taskName, true, this.date, this.startTime, this.endTime);
    }

    @Override
    public boolean hasSameDate(LocalDate date) {
        return this.date.equals(date);
    }

    @Override
    public boolean isBeforeDate(LocalDateTime dateTime) {
        // Will also return true if the date times are equal
        return !this.date.atTime(this.startTime).isAfter(dateTime);
    }

    @Override
    public String toSaveData() {
        return "E|" + super.toSaveData() + "|" + this.date
                + "|" + this.startTime + "|" + this.endTime;
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedStartTime = this.startTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        String formattedEndTime = this.endTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ", "
                + formattedStartTime + " - " + formattedEndTime + ")";
    }
}
