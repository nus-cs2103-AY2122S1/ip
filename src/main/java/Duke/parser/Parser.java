package duke.parser;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInstructionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents the parser of user inputs for Duke. Parses user input for Duke to respond to.
 */
public class Parser {

    public Parser() {}

    /**
     * Parse user input.
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
        case "deadline":
        case "event":
            if (input.length() == 0) {
                throw new InvalidInputException(cmd + " task needs a description.");
            }

            String center = null;
            switch (cmd) {
            case "todo":
                center = "";
                break;
            case "deadline":
                center = "/by";
                break;
            case "event":
                center = "/at";
                break;
            }

            int centerIndex = input.indexOf(center);
            if (centerIndex == -1) {
                throw new InvalidInputException("To create a " + cmd + " task, " + center + " is required.\"");
            }

            String details = "";
            String dateTime = "";

            if (!cmd.equals("todo")) {
                details = input.substring(0, centerIndex).trim();
                dateTime = input.substring(centerIndex).trim();
                if (details.length() == 0 || dateTime.length() <= center.length()) {
                    throw new InvalidInputException(cmd + " task must have details before and after "
                            + center + ".");
                }
            }

            dateTime = dateTime.substring(center.length()).trim();

            switch (cmd) {
            case "todo":
                data.put("details", input);
                return data;
            case "deadline":
                data.put("details", details);
                data.put("deadline", dateTime);
                return data;
            case "event":
                data.put("details", details);
                data.put("timing", dateTime);
                return data;
            }
            break;

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
        default:
            throw new InvalidInstructionException(cmd);
        }

        return data;
    }
}
