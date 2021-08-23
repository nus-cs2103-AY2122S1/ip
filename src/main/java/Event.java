import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class implements a DukeList to be used in storing string from Duke
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class Event extends Task {
    /** The date and time of the deadline task when it starts.*/
    protected LocalDateTime dateTimeStart;

    /** The date and time of the deadline task when it ends.*/
    protected LocalDateTime dateTimeEnd;

    /** The DateTimeFormatter used when printing the Event.*/
    private static final DateTimeFormatter PRINT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /** The DateTimeFormatter when converting time from string.*/
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy ha");

    /** The DateTimeFormatter when converting time with minutes from string.*/
    private static final DateTimeFormatter DATE_MINUTES_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy h:mma");


    /**
     * Constructor for Event class
     * @param name the name of the event
     * @param dateTimeStart the day of the event
     * @param dateTimeEnd the day of the event
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
     * @throws DuchessException Exception thrown when an incorrect format is used for Event.
     */
    public static LocalDateTime[] convertStringToDate (String date, String duration) throws DuchessException {
        try {
            String replacement = duration.replace("am", "AM").replace("pm","PM");
            String[] timeParts = replacement.split("-");
            String startTime = timeParts[0];
            String endTime = timeParts[1];
            LocalDateTime startEvent = LocalDateTime.parse(date + " " + startTime,
                    startTime.contains(":") ? DATE_MINUTES_FORMATTER : DATE_FORMATTER);
            LocalDateTime endEvent = LocalDateTime.parse(date + " " + endTime,
                    endTime.contains(":") ? DATE_MINUTES_FORMATTER : DATE_FORMATTER);
            LocalDateTime[] localDateTimes = {startEvent, endEvent};
            return localDateTimes;
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new DuchessException("Wrong format used.");
        }
    }

    public LocalDateTime getDateTime(){
        return this.dateTimeStart;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + dateTimeStart.format(PRINT_DATE_FORMATTER) + " to "
                    + dateTimeEnd.format(PRINT_DATE_FORMATTER) + ")";
    }
}
