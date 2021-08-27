package duke.task;

import duke.exception.MissingArgumentException;
import duke.util.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event Class represents a task that starts and ends at specific timings.
 *
 * It contains information relating to the task:
 * - description
 * - isDone
 * - eventDate
 * - startTime
 * - endTime
 *
 * @author Benedict Chua
 */
public class Event extends Task {
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructs a new Eveent task from the given description and eventTime.
     *
     * @param description String of the task description.
     * @param eventTime String of the given event date, startTime and endTime.
     */
    public Event(String description, String eventTime) {
        super(description);
        String[] dateInfo = eventTime.split(" ", 3);
        if (dateInfo.length < 3) {
            throw new MissingArgumentException("Date or Time", "Event");
        }
        this.eventDate = Parser.parseDate(dateInfo[0]);
        this.startTime = Parser.parseTime(dateInfo[1]);
        this.endTime = Parser.parseTime(dateInfo[2]);
    }

    /**
     * Constructs a new Eveent task from the given description, eventTime and completion status.
     * Used when loading from a save file.
     *
     * @param completed String indicating the status of completion: 1 if done, 0 if not.
     * @param description String of the task description.
     * @param eventTime String of the given event date, startTime and endTime.
     */
    public Event(String completed, String description, String eventTime) {
        super(description);
        String[] dateInfo = eventTime.split(" ", 3);
        if (dateInfo.length < 3) {
            throw new MissingArgumentException("Date or Time", "Event");
        }
        this.eventDate = Parser.parseDate(dateInfo[0]);
        this.startTime = Parser.parseTime(dateInfo[1]);
        this.endTime = Parser.parseTime(dateInfo[2]);

        if (completed.equals("1")) {
            super.markTaskAsDone();
        }
    }

    /**
     * Formats date into a String for printing.
     *
     * @return String of the formatted date in the form Mmm d yyyy.
     */
    private String formatDate() {
        return eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean isOnDate(String date) {
        return this.eventDate.equals(Parser.parseDate(date));
    }

    @Override
    public String saveAsString() {
        return super.formatString("E", String.format("%s %s %s", this.eventDate, this.startTime, this.endTime));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (by: %s %s-%s)", super.toString(), formatDate(), startTime, endTime);
    }
}