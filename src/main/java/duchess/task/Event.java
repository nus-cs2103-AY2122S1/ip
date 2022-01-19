package duchess.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duchess.main.DuchessException;

/**
 * This class implements an Event task.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class Event extends Task {

    /** The DateTimeFormatter used when printing the Event.*/
    private static final DateTimeFormatter PRINT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /** The DateTimeFormatter when converting time from string.*/
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy ha");

    /** The DateTimeFormatter when converting time with minutes from string.*/
    private static final DateTimeFormatter DATE_MINUTES_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy h:mma");

    /** The date and time of the deadline task when it starts.*/
    protected LocalDateTime dateTimeStart;

    /** The date and time of the deadline task when it ends.*/
    protected LocalDateTime dateTimeEnd;


    /**
     * Constructs an Event.
     * @param name The name of the event.
     * @param dateTimeStart The time which the event starts.
     * @param dateTimeEnd The time which the event ends.
     */
    public Event(String name, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        super(name);
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
    }

    /**
     * Converts the user input string to a LocalDateTime.
     * @param date The user input date as a string.
     * @param duration The user input duration as a string.
     * @return An array of LocalDateTime containing the start and end LocalDateTimes respectively.
     * @throws DuchessException When an incorrect format is used for Event.
     */
    public static LocalDateTime[] convertStringToDates(String date, String duration) throws DuchessException {
        try {
            String replacement = duration.replace("am", "AM").replace("pm", "PM");
            String[] timeParts = replacement.split("-");
            String startTime = timeParts[0];
            String endTime = timeParts[1];
            LocalDateTime startEvent = LocalDateTime.parse(date + " " + startTime,
                    startTime.contains(":") ? DATE_MINUTES_FORMATTER : DATE_FORMATTER);
            LocalDateTime endEvent = LocalDateTime.parse(date + " " + endTime,
                    endTime.contains(":") ? DATE_MINUTES_FORMATTER : DATE_FORMATTER);
            LocalDateTime[] localDateTimes = {startEvent, endEvent};
            return localDateTimes;
        } catch (DateTimeException e) {
            throw new DuchessException("Wrong format used.");
        }
    }

    /**
     * Returns a simplified representation of the Event for easier recovery from save file.
     * @return The file formatted string representation of the Event.
     */
    public String toFileFormat() {
        return String.format("E%s,%s,%s,%s", name, dateTimeStart.format(PRINT_DATE_FORMATTER),
                dateTimeEnd.format(PRINT_DATE_FORMATTER), isDone);
    }

    /**
     * Converts the user input string to a LocalDateTime.
     * @param by The date as text within the file.
     * @return The LocalDateTime representation.
     * @throws DuchessException Exception thrown when an incorrect format is used for Deadline.
     */
    public static LocalDateTime convertTextToDate (String by) {
        return LocalDateTime.parse(by, PRINT_DATE_FORMATTER);
    }

    public LocalDateTime getDateTimeStart() {
        return this.dateTimeStart;
    }

    /**
     * Returns a string representation of the Event, with an [X] marked for done and [ ] as undone.
     * @return the string representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + dateTimeStart.format(PRINT_DATE_FORMATTER) + " to "
                + dateTimeEnd.format(PRINT_DATE_FORMATTER) + ")";
    }

    /**
     * Compares the Event with another object.
     * @param o The object to compare with.
     * @return Whether the object is an Event with the same description and dates.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Event)) {
            return false;
        } else {
            Event e = (Event) o;
            return e.name.equals(this.name) && e.dateTimeStart.equals(this.dateTimeStart)
                    && e.dateTimeEnd.equals(this.dateTimeEnd);
        }
    }
}
