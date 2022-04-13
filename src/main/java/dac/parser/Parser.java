package dac.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import dac.exception.InvalidInputException;
import dac.exception.InvalidInstructionException;

/**
 * Represents the parser of user inputs for Duke. Parses user input for Duke to respond to.
 */
public class Parser {

    public Parser() {}

    /**
     * Gets the index of the center token in the input string.
     *
     * @param center The center token.
     * @param input The input string.
     * @return The index of the center token.
     * @throws InvalidInputException Thrown when the center token does not exist
     * or when the input string is empty.
     */
    private int getCenterIndex(String center, String input) throws InvalidInputException {
        if (input.length() == 0) {
            throw new InvalidInputException("Task description is missing in input.");
        }
        int centerIndex = input.indexOf(center);
        if (centerIndex == -1) {
            throw new InvalidInputException(center + " is missing in input.");
        }
        return centerIndex;
    }

    /**
     * Gets the input before the center token in the input string.
     *
     * @param input The input string.
     * @param centerIndex The index of the center token.
     * @return The input before the center token.
     * @throws InvalidInputException Thrown when there is no input before the center token.
     */
    private String inputBeforeCenter(String input, int centerIndex) throws InvalidInputException {
        String strBeforeCenter = input.substring(0, centerIndex).trim();
        if (strBeforeCenter.length() == 0) {
            throw new InvalidInputException("There must be details before the center.");
        }
        return strBeforeCenter;
    }

    /**
     * Gets the input after the center token in the input string.
     *
     * @param input The input string.
     * @param centerIndex The index of the center token.
     * @param centerLength The length of the center token.
     * @return The input after the center token.
     * @throws InvalidInputException Thrown when there is no input after the center token.
     */
    private String inputAfterCenter(String input, int centerIndex, int centerLength) throws InvalidInputException {
        String strAfterCenter = input.substring(centerIndex).trim();
        if (strAfterCenter.length() <= centerLength) {
            throw new InvalidInputException("There must be details after the center.");
        }
        return strAfterCenter.substring(centerLength).trim();
    }
    /**
     * Parse user input and divides it into the necessary components needed for Duke to respond accordingly.
     *
     * @param input User input.
     * @return HashMap containing the necessary information for Duke to respond to the input.
     * @throws InvalidInputException If user input is invalid.
     * @throws InvalidInstructionException If user input is an invalid instruction.
     * @throws IllegalStateException Exception thrown by the scanner.
     * @throws NoSuchElementException Exception thrown by the scanner.
     */
    public HashMap<String, Object> parse(String input)
            throws InvalidInputException, InvalidInstructionException, IllegalStateException, NoSuchElementException {

        HashMap<String, Object> data = new HashMap<>();
        Scanner scanner = new Scanner(input);
        String cmd = scanner.next();
        data.put("cmd", cmd);
        if (Objects.equals(cmd, "bye")
                || Objects.equals(cmd, "list")) {
            return data;
        }

        assert !Objects.equals(cmd, "bye")
                && !Objects.equals(cmd, "list") : cmd + " should have been returned.";
        input = input.substring(input.indexOf(cmd) + cmd.length()).trim();

        switch (cmd) {
        case "done":
            try {
                int doneIndex = Integer.parseInt(input);
                data.put("index", doneIndex);
                return data;
            } catch (NumberFormatException e) {
                throw new InvalidInputException("To complete a task: enter \"done (task number)\"");
            }
        case "delete":
            try {
                int deleteIndex = Integer.parseInt(input);
                data.put("index", deleteIndex);
                return data;
            } catch (NumberFormatException e) {
                throw new InvalidInputException("To delete a task: enter \"delete (task number)\"");
            }
        case "todo":
            getCenterIndex("", input);
            data.put("details", input);
            return data;
        case "deadline":
            int byIndex = getCenterIndex("/by", input);
            String deadlineDetails = inputBeforeCenter(input, byIndex);
            String deadline = inputAfterCenter(input, byIndex, 3);
            data.put("details", deadlineDetails);
            data.put("deadline", deadline);
            return data;
        case "event":
            int atIndex = getCenterIndex("/at", input);
            String eventDetails = inputBeforeCenter(input, atIndex);
            String timing = inputAfterCenter(input, atIndex, 3);
            data.put("details", eventDetails);
            data.put("timing", timing);
            return data;
        case "date":
            try {
                LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                data.put("date", date);
                return data;
            } catch (DateTimeParseException e) {
                throw new InvalidInputException(
                        "Enter the date after \"date\" in this format: YYYY-MM-DD.");
            }
        case "find":
            data.put("keyword", input);
            break;
        case "sort":
            if (input.length() == 0) {
                data.put("reverse", false);
            } else if (input.equals("reverse")) {
                data.put("reverse", true);
            } else {
                throw new InvalidInputException("Enter \"sort\" to sort the task list.\n"
                        + "Enter \"sort reverse\" to sort the task list in reverse order.");
            }
            break;
        default:
            throw new InvalidInstructionException(cmd);
        }

        return data;
    }
}
