package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.UserInputError;

/**
 * The Event class that represents a task with a date and time period.
 */
public class Event extends Task {

    protected LocalDate date;
    protected String time;

    /**
     * Constructor to create an Event task.
     * Throws an error if datetime is invalid
     *
     * @param description Details of task.
     * @param when Deadline of task.
     * @param done Boolean value that indicates completeness of task.
     * @throws UserInputError Throws error with invalid when String.
     */
    public Event(String description, String when, boolean done) throws UserInputError {
        super(description, Task.Type.EVENT, done);
        assert !description.trim().equals("") : "empty deadline task description";
        try {
            String[] arr = checkDateTimeFormat(when.trim());
            this.date = getDateFromString(arr[0].trim().toLowerCase());
        } catch (DateTimeException e) {
            throw new UserInputError("Invalid datetime format. Please check user guide for more info!");
        }
    }

    /**
     * Checks that both date and time input of the user is accurate and valid.
     * Throws a UserInputError with wrong datetime format.
     *
     * @param datetime Datetime string input of user.
     * @return An array containing the date and time strings.
     * @throws UserInputError Throws error with wrong datetime input format.
     */
    private String[] checkDateTimeFormat(String datetime) throws UserInputError {
        String[] arr = datetime.split(" ", 2);
        if (arr.length < 2) {
            throw new UserInputError("Invalid datetime format. "
                    + "Please ensure you leave a space between date and time.");
        }

        String period = arr[1];
        checkTimeRangeFormat(period);
        return arr;
    }

    /**
     * Checks if input time period is valid and in the correct format.
     * Throws UserInputError if time range is invalid.
     *
     * @param period String representing starting and ending time of event task.
     * @throws UserInputError Throws error if time input is invalid or in the wrong format.
     */
    private void checkTimeRangeFormat(String period) throws UserInputError {
        String[] timings = period.trim().split("-");
        if (timings.length != 2) {
            throw new UserInputError("Invalid time format. "
                    + "Please ensure you leave a dash between starting and ending time.");
        }

        checkTimeFormat(timings[0]);
        checkTimeFormat(timings[1]);

        int end = Integer.parseInt(timings[1].trim());
        int start = Integer.parseInt(timings[0].trim());
        if (end < start || start == end) {
            throw new UserInputError("Invalid time. Please ensure start and end time are logically sound.");
        }

        this.time = timings[0].trim() + " - " + timings[1].trim();
    }

    /**
     * Appends datetime to the String format writeable to Storage file.
     *
     * @return Formatted data as a string
     */
    @Override
    public String convertTaskToString() {
        return super.convertTaskToString() + date + " " + time;
    }

    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " { at: " + formattedDate + " || " + time + " }";
    }
}
