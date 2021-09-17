package side.tasks;

import side.util.DateTime;

/**
 * Represents a task that has to be done before a specified time.
 *
 * It encapsulates the following information:
 * - description
 * - time
 * - isDone
 *
 * @author Lua Yi Da
 */

public class Deadline extends Task {

    private DateTime datetime;

    /**
     * Initialises a new Deadline.
     *
     * @param description String for description given.
     * @param datetimeInput String representing datetime to be converted.
     */
    public Deadline(String description, String datetimeInput) {
        super(description);

        String[] datetime = datetimeInput.trim().split(", ", 2);
        if (datetime.length == 2) {
            this.datetime = new DateTime(datetime[0], datetime[1]);
        } else {
            this.datetime = new DateTime(datetime[0]);
        }
    }

    /**
     * Initialises a new deadline.
     *
     * @param description String for description given.
     * @param datetimeInput String representing datetime to be converted.
     * @param isDone boolean describing if Deadline is done or not.
     */
    public Deadline(String description, String datetimeInput, Boolean isDone) {
        super(description, isDone);

        String[] datetime = datetimeInput.split(", ", 2);
        if (datetime.length == 2) {
            this.datetime = new DateTime(datetime[0], datetime[1]);
        } else {
            this.datetime = new DateTime(datetime[0]);
        }
    }

    /**
     * Gets datetime from Deadline instance.
     *
     * @return Datetime from instance.
     */
    public DateTime getDatetime() {
        return this.datetime;
    }

    /**
     * Overrides toString method to create string matching list format.
     *
     * @return String matching list format.
     */
    @Override
    public String toString() {
        StringBuilder deadlineString = new StringBuilder();
        if (this.isDone) {
            deadlineString.append("[D][x] ");
        } else {
            deadlineString.append("[D][ ] ");
        }
        String deadlineDetails = this.description.replaceFirst("deadline", "")
                + "(by: " + this.datetime.toString() + ")";
        deadlineString.append(deadlineDetails);
        return deadlineString.toString();
    }
}
