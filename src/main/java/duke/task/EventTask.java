package duke.task;

import duke.exception.InvalidInputException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for EventTask object.
     * @param name name of task.
     * @param isDone whether or not task is done.
     * @param start starting date and time of event, format according to DATE_TIME_FORMAT.
     * @param end end date and time of event, of format according to DATE_TIME_FORMAT.
     */
    public EventTask(String name, boolean isDone, String start, String end) throws DateTimeException, InvalidInputException {
        super(name, isDone);
        this.start = LocalDateTime.parse(start, DATE_TIME_FORMAT);
        this.end = LocalDateTime.parse(end, DATE_TIME_FORMAT);
        if (this.end.compareTo(this.start) < 0) {
            throw new InvalidInputException("End of event cannot be before start of event.");
        }
    }

    public EventTask(String name, String start, String end) throws DateTimeException, InvalidInputException {
        super(name);
        this.start = LocalDateTime.parse(start, DATE_TIME_FORMAT);
        this.end = LocalDateTime.parse(end, DATE_TIME_FORMAT);
        if (this.end.compareTo(this.start) < 0) {
            throw new InvalidInputException("End of event cannot be before start of event.");
        }
    }

    /**
     * Checks whether the time of this event overlaps with as a given event.
     * @param eventTask The event to be compared to.
     * @return true if the two events overlap.
     */
    public boolean overlapsWith(EventTask eventTask) {
        boolean startAfterOther = this.start.compareTo(eventTask.start) >= 0;
        boolean startBeforeOtherEnds = this.start.compareTo(eventTask.end) < 0;
        boolean thisStartsDuringOther = startAfterOther && startBeforeOtherEnds;

        boolean startAfterThis = eventTask.start.compareTo(this.start) >= 0;
        boolean startBeforeThisEnds = eventTask.start.compareTo(this.end) < 0;
        boolean otherStartsDuringThis = startAfterThis && startBeforeThisEnds;

        return thisStartsDuringOther || otherStartsDuringThis;
    }


    @Override
    public String formatForFile() {
        String startEndData = SAVE_DATA_MARKER + this.start.format(DATE_TIME_FORMAT)
                + SAVE_DATA_MARKER + this.end.format(DATE_TIME_FORMAT);
        return "E" + super.formatForFile() + startEndData + "\n";
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("d MMM yyyy H':'mm");
        String startTime = this.start.format(dateTimeFormat);
        String endTime = this.end.format(dateTimeFormat);
        return "[E]" + super.toString() + " (" + startTime + " - " + endTime + ")";
    }
}
