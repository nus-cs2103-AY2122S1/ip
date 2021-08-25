package pib.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pib.pibexception.PibException;

/**
 * Event task which contains the task description, and the date of the event
 */
public class Event extends Task {

    private String date;
    private String time;

    private Event(String description, String date, String time, boolean printMessage) {
        super(description, printMessage);
        this.date = date;
        this.time = time;
    }

    private Event(String description, int isDone, String date, String time, boolean printMessage) {
        super(description, isDone, printMessage);
        this.date = date;
        this.time = time;
    }

    /**
     * A public factory method to create an Event task
     *
     * @param details String containing the description, date and time
     * @param printMessage Boolean to indicate whether to print the success message after each Task is added
     * @return Event object with description initialised and isDone set to 0
     * @throws PibException when user inputs blank task description
     * @throws PibException when user inputs wrongly formatted command to create a new Event
     * @throws PibException when user inputs wrongly formatted date/time
     */
    public static Event createEvent(String details, boolean printMessage) throws PibException {
        try {
            int atIndex = details.indexOf("/at ");
            String description = details.substring(0, atIndex).trim();
            if (description.isBlank()) {
                throw new PibException("empty-task-description");
            }
            String[] dateTime = details.substring(atIndex + 4).trim().split(" ");
            String date = LocalDate.parse(dateTime[0].trim()).format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            String time = LocalTime.parse(dateTime[1].trim(), DateTimeFormatter.ofPattern("HHmm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
            return new Event(description, date, time, printMessage);
        } catch (IndexOutOfBoundsException e) {
            throw new PibException("e-wrong-format");
        } catch (DateTimeParseException e) {
            throw new PibException("wrong-datetime-format");
        }
    }

    /**
     * A public factory method to create an Event task
     *
     * @param description task description
     * @param isDone value 0 (false) or 1 (true)
     * @param date String showing date of Event task
     * @param time String showing time of Event task
     * @param printMessage Boolean to indicate whether to print the success message after each Task is added
     * @return Event object with these 4 fields initialised
     */
    public static Event createEvent(String description, int isDone, String date, String time, boolean printMessage) {
        return new Event(description, isDone, date, time, printMessage);
    }

    /**
     * Public method to convert task to a string format used to save inside a .txt file
     *
     * @return string format of Event task to be saved
     */
    public String toDataString() {
        return "E," + getIsDone() + "," + getDescription() + "," + date + "," + time + System.lineSeparator();
    }

    /**
     * A public toString method to add the task type [E] in front of the checkbox, and the date behind the task description
     *
     * @return the string representation of an event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ", " + this.time + ")";
    }
}
