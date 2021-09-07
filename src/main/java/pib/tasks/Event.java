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
            int atIndex = getAtIndex(details);
            String description = getDescriptionPortion(details, atIndex);
            if (description.isBlank()) {
                throw new PibException("empty-task-description");
            }
            String[] dateTime = getDateTimePortion(details, atIndex);
            String date = getDateString(dateTime[0]);
            String time = getTimeString(dateTime[1]);
            return new Event(description, date, time, printMessage);
        } catch (IndexOutOfBoundsException e) {
            throw new PibException("e-wrong-format");
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

    private static int getAtIndex(String details) {
        return details.indexOf("/at ");
    }

    private static String getTimeString(String s) {
        return LocalTime.parse(s.trim(), DateTimeFormatter.ofPattern("HHmm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    private static String getDateString(String s) {
        return LocalDate.parse(s.trim()).format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
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
