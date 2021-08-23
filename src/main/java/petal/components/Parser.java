package petal.components;

import petal.command.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * The Parser deals with comprehending the user's input
 */
public class Parser {

    /**
     * Method that comprehends the message. It splits the input and takes
     * the first word (assumed to be command if format followed) and reacts accordingly
     *
     * @param message User input
     */
    public Command handleInput(String message) {
        message += " "; //So blank inputs can be handled
        String command = message.substring(0, message.indexOf(" "));
        String formatted = message.substring(message.indexOf(' ') + 1).trim();
        switch (command) { //Checks first word in string
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand();
            case "done":
                return new DoneCommand(formatted);
            case "delete":
                return new DeleteCommand(formatted);
            case "todo":
            case "deadline":
            case "event":
                return new TaskCommand(command, formatted);
            case "date":
                return new DateCommand(formatted);
            default:
                return new UnintelligibleCommand();
        }
    }

    /**
     * Static method that parse a given string (if given correctly), creating a LocalTime
     *
     * @param time The given String
     * @return LocalTime object
     * @throws DateTimeParseException Thrown if string cannot be represented as a time object
     */
     public static LocalTime parseTime(String time) throws DateTimeParseException {
        String formattedTime = time.indexOf(":") > 0 ? time
                : time.substring(0, 2) + ":" + time.substring(2);
        return LocalTime.parse(formattedTime);
    }

    /**
     * Static method that parse a given string (if given correctly), creating a LocalDate
     *
     * @param date The given String
     * @return LocalDate object
     * @throws DateTimeParseException Thrown if string cannot be represented as a Date object
     */
    public static LocalDate parseDate(String date) {
        String[] ddMMYY = date.split("/");
        if (ddMMYY[0].length() == 1) { //if user types 2/12/2019 -> 02/12/2019
            ddMMYY[0] = "0" + ddMMYY[0];
        }
        return LocalDate.parse(ddMMYY[2] + "-" + ddMMYY[1] + "-" + ddMMYY[0]);
    }

}
