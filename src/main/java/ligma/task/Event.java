package ligma.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

/**
 * This class represents an Event Task.
 */
public class Event extends Task {
    private LocalDate time;

    private Event(String details, LocalDate time, String meta) {
        super(details, meta);
        this.time = time;
    }

    private Event(String details, LocalDate time, boolean isDone, String meta) {
        super(details, isDone, meta);
        this.time = time;
    }

    /**
     * Factory method that creates an Event object.
     *
     * @param desc                      the description of the event
     * @return                          event created
     * @throws InputMismatchException   if format of event description is wrong
     * @throws DateTimeParseException   if format of time is wrong
     */
    public static Event createEvent(String desc)
            throws InputMismatchException {
        if (desc.contains("/at")) {
            String[] arr = desc.split("/at");
            try {
                LocalDate time = LocalDate.parse(arr[1].trim());
                return new Event(arr[0].trim(), time, desc);
            } catch (DateTimeParseException e) {
                throw new InputMismatchException("Time must be in yyyy-mm-dd format.");
            }
        } else {
            throw new InputMismatchException("Time must be stipulated for events using '/at'.");
        }
    }

    /**
     * Factory method that creates an Event object.
     *
     * @param desc                      the description of the event
     * @param isDone                    whether task has been completed
     * @return                          event created
     * @throws InputMismatchException   if format of event description is wrong
     * @throws DateTimeParseException   if format of time is wrong
     */
    public static Event createEvent(String desc, boolean isDone) throws InputMismatchException {
        if (desc.contains("/at")) {
            String[] arr = desc.split("/at");
            LocalDate time = LocalDate.parse(arr[1].trim());
            return new Event(arr[0].trim(), time, isDone, desc);
        } else {
            throw new InputMismatchException("Time must be stipulated for events using '/at'.");
        }
    }

    @Override
    public String getFullMeta() {
        return "E" + super.getFullMeta();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(),
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
