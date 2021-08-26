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

    private DateTime startDatetime, endDatetime;

    public Event(String description, String startInput, String endInput) {
        super(description);
        String[] startDatetimeArr = startInput.split("\\s+");
        String[] endDatetimeArr = endInput.split("\\s+");

        if (startDatetimeArr.length == 2 && endDatetimeArr.length == 2) {
            this.startDatetime = new DateTime(startDatetimeArr[0].replace(",","")
                    , startDatetimeArr[1].replace(",",""));
            this.endDatetime = new DateTime(endDatetimeArr[0].replace(",","")
                    , endDatetimeArr[1].replace(",",""));
        } else {
            this.startDatetime = new DateTime(startDatetimeArr[0]);
            this.endDatetime = new DateTime(endDatetimeArr[0]);
        }
    }

    public Event(String description, String[] datetimeInputArr, Boolean isDone) {
        super(description, isDone);

        String[] startDatetimeArr = datetimeInputArr[0].split(", ");
        String[] endDatetimeArr = datetimeInputArr[1].split(", ");

        if (startDatetimeArr.length == 2 && endDatetimeArr.length == 2) {
            this.startDatetime = new DateTime(startDatetimeArr[0], startDatetimeArr[1]);
            this.endDatetime = new DateTime(endDatetimeArr[0], endDatetimeArr[1]);
        } else {
            this.startDatetime = new DateTime(startDatetimeArr[0]);
            this.endDatetime = new DateTime(endDatetimeArr[0]);
        }
    }

    public DateTime getStartDatetime() {
        return this.startDatetime;
    }

    public DateTime getEndDatetime() {
        return this.startDatetime;
    }

    @Override
    public String toString() {
        StringBuilder deadlineLine = new StringBuilder();
        if (this.isDone) {
            deadlineLine.append("[E][x] ");
        } else {
            deadlineLine.append("[E][ ] ");
        }
        String deadlineDetails = this.description.replaceFirst("event", "") +
                "(at: " + this.startDatetime + " to " + this.endDatetime + ")";
        deadlineLine.append(deadlineDetails);
        return deadlineLine.toString();
    }
}
