package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.UserInputError;

/**
 * The Deadline class that represents a task with a starting datetime.
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected String time;

    /**
     * Constructor to create a Deadline task.
     *
     * @param description details of task.
     * @param when deadline date of task.
     * @param done Boolean value that indicates completeness of task.
     * @throws UserInputError Throws error with bad user input.
     */
    public Deadline(String description, String when, boolean done) throws UserInputError {
        super(description, Task.Type.DEADLINE, done);
        assert !description.trim().equals("");
        try {
            String[] arr = checkDateTimeFormat(when.trim());
            this.date = getDateFromString(arr[0].trim().toLowerCase());
            this.time = arr[1];
        } catch (DateTimeException e) {
            throw new UserInputError("Wrong datetime format");
        }
    }

    /**
     * Checks that both date and time input of the user is accurate and valid.
     *
     * @param datetime Datetime string input of user.
     * @return An array containing the date and time strings.
     * @throws UserInputError Throws error with wrong datetime input format.
     */
    private String[] checkDateTimeFormat(String datetime) throws UserInputError {
        String[] arr = datetime.split(" ");
        if (arr.length != 2) {
            throw new UserInputError("Invalid datetime format. "
                    + "Please ensure you leave a space between date and time");
        }

        String time = arr[1];
        if (time.length() != 4) { //checks that string adheres to 24h format
            throw new UserInputError("Invalid time input. Please ensure it is in 24h format");
        }

        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(2, 4));

        if (hour < 0 || hour > 23 || min < 0 || min > 59) { //checks hour and min are valid
            throw new UserInputError("Your hour/minute input is invalid. Please check and try again!");
        }
        return arr;
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
        return "[D]" + super.toString() + " (by: " + formattedDate + " " + time + ")";
    }
}
