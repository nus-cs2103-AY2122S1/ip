package janet;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates the methods needed to parse the input given to Janet and convert
 * it into a Command that Janet understands.
 */
public class Parser {

    /**
     * Returns a Command that Janet understands from a string passed to Janet by
     * the user.
     *
     * @param input String that user passed to Janet
     * @return Command that Janet is able to execute
     * @throws JanetException If invalid or insufficient arguments are provided
     */
    public static Command parseUserInput(String input) throws JanetException {
        String[] inputStringParts = input.split(" ");
        String operation = inputStringParts[0];
        assert(inputStringParts[0] != null);
        switch (operation) {
        case "bye":
            // Fallthrough
        case "list":
            return new Command(operation);
        case "done":
            return parseDone(inputStringParts);
        case "todo":
            return parseTodo(input, inputStringParts);
        case "deadline":
            return parseDeadline(input, inputStringParts);
        case "event":
            return parseEvent(input, inputStringParts);
        case "delete":
            return parseDelete(inputStringParts);
        case "find":
            return parseFind(input, inputStringParts);
        case "schedule":
            return parseSchedule(input, inputStringParts);
        default:
            throw new JanetException(Ui.UNRECOGNISED_OPERATION);
        }
    }

    private static Command parseDone(String[] inputStringParts) throws JanetException {
        String operation = inputStringParts[0];
        if (inputStringParts.length == 1) {
            throw new JanetException(Ui.EXPECTED_DONE_INDEX_GOT_NONE);
        }
        try {
            int doneTaskNum = Integer.parseInt(inputStringParts[1]);
            return new Command(operation, doneTaskNum);
        } catch (NumberFormatException e) {
            throw new JanetException(Ui.EXPECTED_DONE_INDEX_GOT_OTHER);
        }
    }

    private static Command parseTodo(String input, String[] inputStringParts) throws JanetException {
        if (inputStringParts.length == 1) {
            throw new JanetException(Ui.EXPECTED_TO_DO_DESCRIPTION);
        }

        String operation = inputStringParts[0];
        String description = input.substring(operation.length() + 1);
        return new Command(operation, description);
    }

    private static Command parseDeadline(String input, String[] inputStringParts) throws JanetException {
        String operation = inputStringParts[0];
        String[] descriptionAndBy = input.substring(operation.length() + 1).split("/by ");

        // Guard clauses
        if (inputStringParts.length == 1) {
            throw new JanetException(Ui.EXPECTED_DEADLINE_DESCRIPTION);
        }
        if (descriptionAndBy.length == 1) {
            throw new JanetException(Ui.EXPECTED_DEADLINE_BY);
        }

        String description = descriptionAndBy[0];
        String by = descriptionAndBy[1];
        return new Command(operation, description, by);
    }

    private static Command parseEvent(String input, String[] inputStringParts) throws JanetException {
        String operation = inputStringParts[0];
        String[] descriptionAndAt = input.substring(operation.length() + 1).split("/at ");

        // Guard clauses
        if (inputStringParts.length == 1) {
            throw new JanetException(Ui.EXPECTED_EVENT_DESCRIPTION);
        }
        if (descriptionAndAt.length == 1) {
            throw new JanetException(Ui.EXPECTED_EVENT_AT);
        }

        String description = descriptionAndAt[0];
        String at = descriptionAndAt[1];
        return new Command(operation, description, at);
    }

    private static Command parseDelete(String[] inputStringParts) throws JanetException {
        if (inputStringParts.length == 1) {
            throw new JanetException(Ui.EXPECTED_DELETED_INDEX_GOT_NONE);
        }

        String operation = inputStringParts[0];
        try {
            int indexOfTaskToDelete = Integer.parseInt(inputStringParts[1]);
            return new Command(operation, indexOfTaskToDelete);
        } catch (NumberFormatException e) {
            throw new JanetException(Ui.EXPECTED_DELETED_INDEX_GOT_OTHER);
        }
    }

    private static Command parseFind(String input, String[] inputStringParts) throws JanetException {
        if (inputStringParts.length == 1) {
            throw new JanetException(Ui.EXPECTED_SEARCH_QUERY);
        }

        String operation = inputStringParts[0];
        return new Command(operation, input.substring(operation.length() + 1));
    }

    private static Command parseSchedule(String input, String[] inputStringParts) throws JanetException {
        if (inputStringParts.length == 1) {
            throw new JanetException(Ui.EXPECTED_SCHEDULE_QUERY);
        }
        String operation = inputStringParts[0];
        String date = input.substring(operation.length() + 1);
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new JanetException(Ui.EXPECTED_DATE_GOT_OTHER);
        }
        return new Command(operation, date);
    }

}
