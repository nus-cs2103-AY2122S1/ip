package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import duke.exceptions.WrongInputException;




public class Event extends Task {
    protected String at;
    protected String oldAt;

    /**
     * Constructor for Event
     * @param name this is the description of the event
     * @param at this is the timing for the event
     */
    public Event(String name, String at) {
        super(name.trim());
        this.at = at;
        this.oldAt = at;
    }

    /**
     * Returns an event based on given description and time
     * @param name description of task
     * @param at time to task
     * @return an Event task
     * @throws WrongInputException when input format is not correct
     */
    public static Event createEvent(String name, String at) throws WrongInputException {
        if (at.equals("")) {
            return new Event(name, at);
        } else {
            return new Event(name, processDateAndTime(at));
        }
    }

    private static String processDateAndTime(String dateAndTime) throws WrongInputException {
        String input = dateAndTime.trim();
        String[] list = input.split(" ");
        String output = "";

        if (list.length != 2) {
            throw new WrongInputException("You must enter Date and Time in this format: dd/MM/yyyy HHmm");
        }

        String date = list[0];
        String time = list[1];

        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
            DateTimeFormatter resultDateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
            DateTimeFormatter resultTimeFormat = DateTimeFormatter.ofPattern("HH.mm a");

            output = " " + LocalDate.parse(date, dateFormat).format(resultDateFormat).toString() + ", "
                    + LocalTime.parse(time, timeFormat).format(resultTimeFormat).toString();

            return output;
        } catch (DateTimeParseException e) {
            throw new WrongInputException("You must enter Date and Time in this format: dd/MM/yyyy HHmm");
        }
    }

    @Override
    public Task snoozeTask() {
        String[] list = this.at.split(", ");
        String date = list[0].trim();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate newDate = LocalDate.parse(date, dateFormat).plus(1, ChronoUnit.WEEKS);
        this.at = " " + newDate.toString() + ", " + list[1];

        return this;
    }


    @Override
    public String toString() {
        if (!at.equals("")) {
            return "[E]" + super.toString() + " (at:" + at + ")";
        } else {
            return "[E]" + super.toString();
        }
    }
}
