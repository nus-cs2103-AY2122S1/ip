package misaki.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import misaki.exceptions.DeadlineFormatException;
import misaki.exceptions.EmptyDescriptionException;
import misaki.exceptions.EventFormatException;
import misaki.exceptions.OutOfBoundException;

/**
 * Represents a parser that interprets the user input.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class Parser {
    private String input;
    private String[] inputCommandAndDescription;

    /**
     * A constructor for Parser.
     *
     * @param input User input.
     */
    public Parser(String input) {
        this.input = input;
        this.inputCommandAndDescription = input.split(" ", 2);
    }

    /**
     * Returns the command word.
     *
     * @return String of the first word.
     */
    public String getCommandWord() {
        return inputCommandAndDescription[0];
    }

    /**
     * Returns an integer of the index.
     *
     * @param size Size of current TaskList.
     * @return Integer of index.
     * @throws OutOfBoundException If index is less than 1 or more than size of TaskList.
     */
    public int getIndex(int size) throws OutOfBoundException {
        int index = Integer.parseInt(input.split(" ")[1]);
        if (index > size || index < 1) {
            throw new OutOfBoundException(size);
        }
        index -= 1;
        assert index >= 0 && index < size : "index x not within valid range";
        return index;
    }

    /**
     * Returns description of the to-do task.
     *
     * @return String description of the to-do task.
     * @throws EmptyDescriptionException If user input an empty description.
     */
    public String getTodoDescription() throws EmptyDescriptionException {
        if (inputCommandAndDescription[1].trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        return inputCommandAndDescription[1];
    }

    /**
     * Returns description of the deadline task.
     *
     * @return String description of the deadline task.
     * @throws EmptyDescriptionException If user input an empty description.
     * @throws DeadlineFormatException   If user input an invalid deadline format.
     */
    public String getDeadlineDescription() throws EmptyDescriptionException, DeadlineFormatException {
        if (inputCommandAndDescription[1].trim().isEmpty()) {
            throw new EmptyDescriptionException();
        } else if (!inputCommandAndDescription[1].contains("/by ")) {
            throw new DeadlineFormatException();
        }
        String deadlineDescription = inputCommandAndDescription[1];
        return deadlineDescription.split("/by ", 2)[0].trim();
    }

    /**
     * Returns the date of a deadline task.
     *
     * @return String of the deadline task due date.
     * @throws DeadlineFormatException If user input an invalid deadline format.
     */
    public String getDeadlineDate() throws DeadlineFormatException {
        String deadlineDescription = inputCommandAndDescription[1];
        String date = deadlineDescription.split("/by ", 2)[1].trim();
        date = date.replace(" ", "T");
        if (!isValidDateTime(date)) {
            throw new DeadlineFormatException();
        }
        return date;
    }

    /**
     * Returns description of the event task.
     *
     * @return String description of the event task.
     * @throws EmptyDescriptionException If user input an empty description.
     * @throws EventFormatException      If user input an invalid event format.
     */
    public String getEventDescription() throws EmptyDescriptionException, EventFormatException {
        if (inputCommandAndDescription[1].trim().isEmpty()) {
            throw new EmptyDescriptionException();
        } else if (!inputCommandAndDescription[1].contains("/at ")) {
            throw new EventFormatException();
        }
        String eventDescription = inputCommandAndDescription[1];
        return eventDescription.split("/at ", 2)[0].trim();
    }

    /**
     * Returns the start and end dates of an event task.
     *
     * @return String of the start and end dates.
     * @throws EventFormatException If user input an invalid event format.
     */
    public String getEventDate() throws EventFormatException {
        String deadlineDescription = inputCommandAndDescription[1];
        String date = deadlineDescription.split("/at ", 2)[1].trim();
        date = date.replace(" ", "T");
        if (!isValidDateTime(date)) {
            throw new EventFormatException();
        }
        return date;
    }

    /**
     * Returns string of keyword input by user.
     *
     * @return String representation of keyword.
     */
    public String getKeyword() throws EmptyDescriptionException {
        if (inputCommandAndDescription[1].trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        return inputCommandAndDescription[1];
    }

    /**
     * Returns true if user enter a valid DateTime format, false if invalid.
     *
     * @param date User input date.
     * @return Boolean value that represents either valid or invalid date.
     */
    public boolean isValidDateTime(String date) {
        Boolean isValid = true;
        try {
            date = date.replace("T", " ");
            LocalDateTime dateTime = LocalDateTime.parse(date);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm a");
            dateTime.format(formatter);
        } catch (DateTimeParseException e) {
            isValid = false;
        }
        return isValid;
    }
}
