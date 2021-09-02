package duke.Tasks;

import duke.Exceptions.WrongInputException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event
     * @param name this is the description of the event
     * @param at this is the timing for the event
     */
    public Event(String name, String at) {
        super(name.trim());
        this.at = at;
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
            DateTimeFormatter dateF = DateTimeFormatter.ofPattern("d/M/yyyy");
            DateTimeFormatter timeF = DateTimeFormatter.ofPattern("HHmm");
            DateTimeFormatter resultDF = DateTimeFormatter.ofPattern("dd MMM yyyy");
            DateTimeFormatter resultTF = DateTimeFormatter.ofPattern("HH.mm a");

            output = " " + LocalDate.parse(date, dateF).format(resultDF).toString()
                    + ", " + LocalTime.parse(time, timeF).format(resultTF).toString();
            return output;
        } catch (DateTimeParseException e) {
            throw new WrongInputException("You must enter Date and Time in this format: dd/MM/yyyy HHmm");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}
