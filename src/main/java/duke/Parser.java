package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A class containing methods that parse relevant data from strings.
 *
 * @author Toh Wang Bin
 */
public class Parser {

    /**
     * Parses a string into a LocalDate object.
     *
     * @param str The string to be parsed.
     * @return A LocalDate object created byb parsing the input string.
     * @throws IllegalArgumentException When an invalid string is parsed.
     */
    public static LocalDate parseDate(String str) throws IllegalArgumentException {
        LocalDate date;
        try {
            date = LocalDate.parse(str);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Date could not be parsed!");
        }
        return date;
    }

    /**
     * Parses a string into a Task object.
     *
     * @param str The string to be parsed.
     * @return A task object created by parsing the input string.
     */
    public static Task parseData(String str) {
        String[] splitArray = str.split("\\|");
        switch (splitArray[0]) {
        case "E":
            Task event = new Events(splitArray[2], Parser.parseDate(splitArray[3]));
            if (splitArray[1].equals("1")) { event.setCompleted(); }
            return event;
        //no need for break, as function has terminated at the return statement
        case "D":
            Task deadline = new Deadlines(splitArray[2], Parser.parseDate(splitArray[3]));
            if (splitArray[1].equals("1")) { deadline.setCompleted(); }
            return deadline;
        //no need for break, as function has terminated at the return statement
        //last case will always be "T"
        default:
            Task todo = new Todos(splitArray[2]);
            if (splitArray[1].equals("1")) { todo.setCompleted(); }
            return todo;
        }
    }
}
