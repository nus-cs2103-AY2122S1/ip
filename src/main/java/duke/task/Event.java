package duke.task;


import java.time.LocalDateTime;

import duke.DukeException;
import duke.Parser;

/**
 * A task with that spans across a period of time.
 *
 * @author Aiken Wong
 */
public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected String taskType = "[E]";
    protected boolean isDateOnly = false;


    /**
     * Constructor for Event.
     *
     * @param description The description of the Event.
     * @param startDateTime The start date and/or time of the Event.
     * @param endDateTime The end date and/or time of the Event.
     * @param isDone Represents whether the task has been completed.
     * @param isDateOnly Represents whether only date is given (without time).
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean isDone,
                 boolean isDateOnly) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isDone = isDone;
        this.isDateOnly = isDateOnly;
    }

    /**
     * Constructor for Event.
     *
     * @param description The description of the Event.
     * @param startDateTime The start date and/or time of the Event.
     * @param endDateTime The end date and/or time of the Event.
     * @param isDateOnly Represents whether only date is given (without time).
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean isDateOnly) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isDateOnly = isDateOnly;
    }

    /**
     * Factory constructor for Event. Parses String input to obtain date and/or time for start and end dates.
     *
     * @param description The description of the Event.
     * @param input The input date and/time of an Event.
     * @return Event with the given inputs.
     * @throws DukeException
     */
    public static Event of(String description, String input) throws DukeException {

        String exceptionMessage = "Wrong format for event timeline Sir/Mdm. Please use either formats:\n"
            + "'DATE TIME to DATE TIME' or 'DATE to DATE' \n"
            + "Hint: Use 'to' keyword and ensure that start and end date either both\n"
            + "include TIME or both exclude TIME\n"
            + "Examples for DATE TIME to DATE TIME: 13/2/2019 1800 to 13/2/2019 1900\n"
            + "Examples for DATE: 13/2/2019 to 14/2/2019";

        String[] dateTimes = input.split("to");
        if (dateTimes.length != 2) {
            throw new DukeException(exceptionMessage);
        }

        LocalDateTime startDate;
        LocalDateTime endDate;
        boolean isDateOnly;

        String[] dateTimeStartInput = dateTimes[0].trim().split(" ");
        String[] dateTimeEndInput = dateTimes[1].trim().split(" ");
        if (dateTimeStartInput.length == 1 && dateTimeEndInput.length == 1) {

            startDate = Parser.parseDateAndTime(dateTimeStartInput[0], "00:00");
            endDate = Parser.parseDateAndTime(dateTimeEndInput[0], "00:00");
            isDateOnly = true;

        } else if (dateTimeStartInput.length == 2 && dateTimeStartInput.length == 2) {
            startDate = Parser.parseDateAndTime(dateTimeStartInput[0], dateTimeStartInput[1]);
            endDate = Parser.parseDateAndTime(dateTimeEndInput[0], dateTimeEndInput[1]);

            assert startDate.compareTo(endDate) < 0 : "startDate should be earlier than endDate";

            isDateOnly = false;
        } else {
            throw new DukeException(exceptionMessage);
        }

        if (startDate.compareTo(endDate) > 0) {
            throw new DukeException("The end date must come after the start date Sir/Mdm!");
        }

        return new Event(description, startDate, endDate, isDateOnly);

    }


    /**
     * Getter for isDateOnly.
     *
     * @return Gives isDateOnly
     */
    public boolean getIsDateOnly() {
        return isDateOnly ? true : false;
    }


    /**
     * Getter for the start date of the event.
     *
     * @return Start date.
     */
    public LocalDateTime getStartDateTime() {
        return LocalDateTime.parse(startDateTime.toString());
    }

    /**
     * Getter for the end date of the event.
     *
     * @return End date.
     */
    public LocalDateTime getEndDateTime() {
        return LocalDateTime.parse(endDateTime.toString());
    }

    @Override
    public String toString() {
        return taskType + super.toString() + " (at: " + Parser.getDateTimeString(this.startDateTime, isDateOnly)
            + " to " + Parser.getDateTimeString(this.endDateTime, isDateOnly) + ")";
    }

    @Override
    protected LocalDateTime getComparisonTime() {
        return this.getStartDateTime();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Event) {
            Event event = (Event) o;

            return description.equals(event.description) && isDateOnly == event.isDateOnly
                && isDone == event.isDone && startDateTime.equals(event.startDateTime)
                && endDateTime.equals(event.endDateTime);
        }
        return false;
    }


}

