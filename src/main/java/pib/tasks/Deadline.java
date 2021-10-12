package pib.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pib.pibexception.PibException;
import pib.utility.Ui;

/**
 * Deadline task which contains the task description, and the date of the deadline
 */
public class Deadline extends Task {

    private String date;
    private String time;

    private Deadline(String description, String date, String time, boolean printMessage) {
        super(description, printMessage);
        this.date = date;
        this.time = time;
    }

    private Deadline(String description, int isDone, String date, String time, boolean printMessage) {
        super(description, isDone, printMessage);
        this.date = date;
        this.time = time;
    }

    /**
     * Creates a Deadline task
     *
     * @param details String containing the description, date and time
     * @param printMessage Boolean to indicate whether to print the success message after each Task is added
     * @return Deadline object with description initialised and isDone set to 0
     * @throws PibException when user inputs blank task description
     * @throws PibException when user inputs wrongly formatted command to create a new Deadline
     * @throws PibException when user inputs wrongly formatted date/time
     */
    public static Deadline createDeadline(String details, boolean printMessage) throws PibException {
        assert details != null;
        try {
            int byIndex = getByIndex(details);
            String description = getDescriptionPortion(details, byIndex);
            if (description.isBlank()) {
                throw new PibException("empty-task-description");
            }
            assert !details.isBlank();
            String[] dateTime = getDateTimePortion(details, byIndex);
            String date = getDateString(dateTime[0]);
            String time = getTimeString(dateTime[1]);
            return new Deadline(description, date, time, printMessage);
        } catch (IndexOutOfBoundsException e) {
            throw new PibException("d-wrong-format");
        } catch (DateTimeParseException e) {
            throw new PibException("wrong-datetime-format");
        }
    }

    private static String[] getDateTimePortion(String details, int atIndex) {
        return details.substring(atIndex + 4).trim().split(" ");
    }

    private static String getDescriptionPortion(String details, int atIndex) {
        return details.substring(0, atIndex).trim();
    }

    private static int getByIndex(String details) {
        return details.indexOf("/by ");
    }

    private static String getTimeString(String s) {
        return LocalTime.parse(s.trim(), DateTimeFormatter.ofPattern("HHmm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    private static String getDateString(String s) {
        return LocalDate.parse(s.trim()).format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Creates a Deadline task using saved data
     *
     * @param description task description
     * @param isDone value 0 (false) or 1 (true)
     * @param date String showing date of Deadline task
     * @param time String showing time of Deadline task
     * @param printMessage Boolean to indicate whether to print the success message after each Task is added
     * @return Deadline object with these 4 fields initialised
     */
    public static Deadline createDeadline(String description, int isDone, String date, String time, boolean printMessage) {
        assert description != null;
        assert !description.isBlank();
        assert date != null;
        assert !date.isBlank();
        assert time != null;
        assert !time.isBlank();
        return new Deadline(description, isDone, date, time, printMessage);
    }

    /**
     * Converts task to a string format used to save inside a .txt file
     *
     * @return string format of Deadline task to be saved
     */
    public String toDataString() {
        return "D," + getIsDone() + "," + getDescription() + "," + date + "," + time + System.lineSeparator();
    }

    /**
     * Returns a string with "[D]", then the checkbox, then the task description, then the date
     *
     * @return the string representation of a Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ", " + this.time + ")";
    }

    /**
     * Updates the task date or time to a new value
     *
     * @param s specifies whether to update the date or time
     * @param newValue new value to update the date/time with
     * @throws PibException when s is blank or when new date/time is wrongly formatted
     */
    public String editDateTime(String s, String newValue) throws PibException {
        if (newValue.isBlank()) {
            throw new PibException("empty-new-value");
        }
        try {
            if (s.equals("date")) {
                date = getDateString(newValue);
            } else if (s.equals("time")) {
                time = getTimeString(newValue);
            } else {
                assert false;
            }
            return Ui.printUpdateSuccessful();
        } catch (DateTimeParseException e) {
            throw new PibException("wrong-edit-datetime-format");
        }
    }
}
