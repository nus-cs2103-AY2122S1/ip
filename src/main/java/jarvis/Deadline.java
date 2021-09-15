package jarvis;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for tasks that need to be done before a specific date/time
 */
public class Deadline extends Task {
    protected String by;
    protected String timeToDisplay;
    protected LocalDateTime deadline;

    /**
     * Creates a task with a deadline
     *
     * @param description The name/description of the task
     * @param by The deadline for the task (in the format dd/mm/yyyy hh:mm - in 24 hours format)
     */
    public Deadline(String description, String by) throws JarvisException {
        super(description);
        assert !(description.equals("")) : "Deadline description is empty";

        assert (by.length() == 16) : "Timestamp is incorrect.";
        this.by = by;

        try {
            // Extracting the date, month and year numbers from the 'by' string
            int date = Integer.parseInt(by.substring(0, 2));
            assert (date > 0 && date < 32) : "Date is not valid";

            int month = Integer.parseInt(by.substring(3, 5));
            assert (month > 0 && month < 13) : "Month is not valid";

            int year = Integer.parseInt(by.substring(6, 10));
            assert (year > 0) : "Year is not valid";

            // Extracting the event 'by' hour and minute numbers from the 'time' string
            int hour = Integer.parseInt(by.substring(11, 13));
            assert (hour >= 0 && hour < 24) : "Hour is not valid";

            int min = Integer.parseInt(by.substring(14, 16));
            assert (min >= 0 && min < 60) : "Minute is not valid";

            // Creating LocalDateTime objects for the 'by' of the deadline so that its format can be changed later
            LocalDateTime taskDate = LocalDateTime.of(year, month, date, hour, min);

            // Formatting the deadline to be displayed to users
            this.timeToDisplay = taskDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
            deadline = taskDate;
        } catch (NumberFormatException | DateTimeException e) {
            throw new JarvisException(Ui.INVALID_DATE_OT_TIMING);
        }
    }

    /**
     * Returns the deadline of a given deadline task
     *
     * @return the deadline
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the deadline task as a string that is to be displayed to the user
     *
     * @return the deadline task as a string that is to be displayed to the user
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + timeToDisplay + ")";
    }

    /**
     * Returns the deadline task as a string that is to be saved in user's hard disk
     *
     * @return the deadline task as a string that is to be saved in user's hard disk
     */
    @Override
    public String toPrintToFile() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
