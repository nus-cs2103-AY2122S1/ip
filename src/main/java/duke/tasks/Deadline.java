package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.UserInputError;

/**
 * The Deadline class that represents a task with a date and optional time input.
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected String time;

    /**
     * Constructor to create a Deadline task.
     * Throws an error if datetime is invalid.
     *
     * @param description details of task.
     * @param when deadline date of task.
     * @param done Boolean value that indicates completeness of task.
     * @throws UserInputError Throws error with bad user input.
     */
    public Deadline(String description, String when, boolean done) throws UserInputError {
        super(description, Task.Type.DEADLINE, done);
        assert !description.trim().equals("") : "empty deadline task description";
        try {
            String[] arr = checkDateTimeFormat(when.trim());
            this.date = getDateFromString(arr[0].trim().toLowerCase());
            if (arr.length == 2) {
                this.time = arr[1];
            } else {
                this.time = "";
            }
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
        String[] arr = datetime.trim().split(" ", 2);
        if (arr.length < 1) {
            throw new UserInputError("Invalid datetime format. "
                    + "Please ensure you leave a space between date and time");
        }

        if (arr.length == 2) { //if there exists time input, check time format
            String time = arr[1];
            checkTimeFormat(time);
            arr[1] = time.trim();
        }
        return arr;
    }

    /**
     * Appends datetime to the String format writeable to Storage file.
     *
     * @return Formatted data as a string.
     */
    @Override
    public String convertTaskToString() {
        return super.convertTaskToString() + date + " " + time;
    }

    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formatTime = time.equals("") ? "" : " || " + time;
        return "[D]" + super.toString() + " { by: " + formattedDate + formatTime + " }";
    }
}
