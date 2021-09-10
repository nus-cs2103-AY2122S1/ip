package side.tasks;

import side.util.DateTime;

/**
 * Represents a task that has to be done at a specified time.
 *
 * It encapsulates the following information:
 * - description
 * - time
 * - isDone
 *
 * @author Lua Yi Da
 */

public class Event extends Task {

    private DateTime startDatetime;
    private DateTime endDatetime;

    /**
     * Initialises a new Event.
     *
     * @param description String for description given.
     * @param startInput String representing start datetime of event.
     * @param endInput String representing end datetime of event.
     */
    public Event(String description, String startInput, String endInput) {
        super(description);
        String[] startDatetimeArr = startInput.split("\\s+");
        String[] endDatetimeArr = endInput.split("\\s+");

        boolean isStartDatetime = startDatetimeArr.length == 2;
        boolean isEndDatetime = endDatetimeArr.length == 2;
        if (isStartDatetime && isEndDatetime) {
            this.startDatetime = new DateTime(startDatetimeArr[0].replace(",", ""),
                    startDatetimeArr[1].replace(",", ""));
            this.endDatetime = new DateTime(endDatetimeArr[0].replace(",", ""),
                    endDatetimeArr[1].replace(",", ""));
        } else {
            this.startDatetime = new DateTime(startDatetimeArr[0]);
            this.endDatetime = new DateTime(endDatetimeArr[0]);
        }
    }

    /**
     * Initialises a new event.
     *
     * @param description String for description given.
     * @param datetimeInputArr String array representing input datetimes to be converted.
     * @param isDone boolean describing if Event is done or not.
     */
    public Event(String description, String[] datetimeInputArr, Boolean isDone) {
        super(description, isDone);

        String[] startDatetimeArr = datetimeInputArr[0].split(", ");
        String[] endDatetimeArr = datetimeInputArr[1].split(", ");

        boolean isStartDatetime = startDatetimeArr.length == 2;
        boolean isEndDatetime = endDatetimeArr.length == 2;
        if (isStartDatetime && isEndDatetime) {
            this.startDatetime = new DateTime(startDatetimeArr[0], startDatetimeArr[1]);
            this.endDatetime = new DateTime(endDatetimeArr[0], endDatetimeArr[1]);
        } else {
            this.startDatetime = new DateTime(startDatetimeArr[0]);
            this.endDatetime = new DateTime(endDatetimeArr[0]);
        }
    }

    /**
     * Gets starting datetime for Event instance.
     *
     * @return Starting datetime for instance.
     */
    public DateTime getStartDatetime() {
        return this.startDatetime;
    }

    /**
     * Gets end datetime for Event instance.
     *
     * @return End datetime for instance.
     */
    public DateTime getEndDatetime() {
        return this.endDatetime;
    }

    /**
     * Overrides toString method to create string matching list format.
     *
     * @return String matching list format.
     */
    @Override
    public String toString() {
        StringBuilder eventString = new StringBuilder();
        if (this.isDone) {
            eventString.append("[E][x] ");
        } else {
            eventString.append("[E][ ] ");
        }
        String eventDetails = this.description.replaceFirst("event", "")
                + "(at: " + this.startDatetime.toString() + " to " + this.endDatetime.toString() + ")";
        eventString.append(eventDetails);
        return eventString.toString();
    }
}
