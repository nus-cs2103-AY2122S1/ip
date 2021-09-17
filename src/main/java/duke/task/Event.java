package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.exception.MissingArgumentException;
import duke.util.Parser;

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
    private static final String EVENT_IDENTIFIER = "E";
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
        assert !description.trim().isEmpty() : "Event was created with empty description";

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
     * @param completionStatus String indicating the status of completion: 1 if done, 0 if not.
     * @param description String of the task description.
     * @param eventTime String of the given event date, startTime and endTime.
     */
    public Event(CompletionStatus completionStatus, String description, String eventTime) {
        super(description);
        assert !description.trim().isEmpty() : "Event was created with empty description";

        String[] dateInfo = eventTime.split(" ", 3);
        if (dateInfo.length < 3) {
            throw new MissingArgumentException("Date or Time", "Event");
        }
        this.eventDate = Parser.parseDate(dateInfo[0]);
        this.startTime = Parser.parseTime(dateInfo[1]);
        this.endTime = Parser.parseTime(dateInfo[2]);

        if (completionStatus.equals(CompletionStatus.COMPLETED)) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOnDate(String date) {
        return this.eventDate.equals(Parser.parseDate(date));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToString() {
        return super.formatString(EVENT_IDENTIFIER, String.format("%s %s %s", this.eventDate, this.startTime,
            this.endTime));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDate getDate() {
        return this.eventDate;
    }

    /**
     * Returns the start time of the Event.
     * {@inheritDoc}
     */
    @Override
    public LocalTime getTime() {
        return this.startTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkDueBeforeDate(LocalDate date) {
        LocalDate dateNow = LocalDate.now();
        boolean isNotOverdue = this.eventDate.isAfter(dateNow) || this.eventDate.isEqual(dateNow);
        boolean isBeforeDate = this.eventDate.isEqual(date) || this.eventDate.isBefore(date);
        return isNotOverdue && isBeforeDate;
    }

    @Override
    public String toString() {
        return String.format("[%S]%s (by: %s %s-%s)", EVENT_IDENTIFIER, super.toString(), formatDate(), this.startTime,
            this.endTime);
    }
}
