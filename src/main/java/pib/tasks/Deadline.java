package pib.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pib.pibexception.PibException;

/**
 * Deadline task which contains the task description, and the date of the deadline
 */
public class Deadline extends Task {

    private String date;
    private String time;

    private Deadline(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    private Deadline(String description, int isDone, String date, String time) {
        super(description, isDone);
        this.date = date;
        this.time = time;
    }

    /**
     * A public factory method to create a Deadline task
     *
     * @param details String containing the description, date and time
     * @return Deadline object with description initialised and isDone set to 0
     * @throws PibException when user inputs blank task description
     * @throws PibException when user inputs wrongly formatted command to create a new Deadline
     * @throws PibException when user inputs wrongly formatted date/time
     */
    public static Deadline createDeadline(String details) throws PibException {
        try {
            int byIndex = details.indexOf("/by ");
            String description = details.substring(0, byIndex).trim();
            if (description.isBlank()) {
                throw new PibException("empty-task-description");
            }
            String[] dateTime = details.substring(byIndex + 4).trim().split(" ");
            String date = LocalDate.parse(dateTime[0].trim()).format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            String time = LocalTime.parse(dateTime[1].trim(), DateTimeFormatter.ofPattern("HHmm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
            return new Deadline(description, date, time);
        } catch (IndexOutOfBoundsException e) {
            throw new PibException("d-wrong-format");
        } catch (DateTimeParseException e) {
            throw new PibException("wrong-datetime-format");
        }
    }

    /**
     * A public factory method to create a Deadline task
     *
     * @param description task description
     * @param isDone value 0 (false) or 1 (true)
     * @param date String showing date of Deadline task
     * @param time String showing time of Deadline task
     * @return Deadline object with these 4 fields initialised
     */
    public static Deadline createDeadline(String description, int isDone, String date, String time) {
        return new Deadline(description, isDone, date, time);
    }

    /**
     * Public method to convert task to a string format used to save inside a .txt file
     *
     * @return string format of Deadline task to be saved
     */
    public String toDataString() {
        return "D," + getIsDone() + "," + getDescription() + "," + date + "," + time + System.lineSeparator();
    }

    /**
     * A public toString method to add the task type [D] in front of the checkbox, and the date behind the task description
     *
     * @return the string representation of a Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ", " + this.time + ")";
    }
}
