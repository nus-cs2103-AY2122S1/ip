package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DateParsingFailException;
import duke.exception.EmptyCommandException;
import duke.exception.EmptyDescriptionException;
import duke.exception.ExtraArgumentException;
import duke.exception.IncompleteDescriptionException;
import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidDescriptionException;
import duke.exception.MissingArgumentException;
import duke.exception.TaskNotFoundException;

/**
 * A class that checks if it should throw exception.
 */
public class ExceptionChecker {

    /**
     * Throws EmptyCommandException if the command is either empty or consists of whitespaces only.
     *
     * @param command The command entered by the user.
     * @throws EmptyCommandException The exception thrown when the empty command is detected.
     */
    public static void checkEmptyCommand(String command) throws EmptyCommandException {
        if (command.isBlank() || command.isEmpty()) {
            throw new EmptyCommandException("Empty command is not allowed here!");
        }
    }

    /**
     * Throws ExtraArgumentException if the command contains extraneous argument.
     *
     * @param command The command entered by the user.
     * @throws ExtraArgumentException The exception thrown when the extraneous argument is detected.
     */
    public static void checkExtraArgument(String type, String command) throws ExtraArgumentException {
        boolean hasExtra = command.split(" ", 2).length > 1;
        if (hasExtra) {
            throw new ExtraArgumentException(type);
        }
    }

    /**
     * Throws EmptyDescriptionException if the task has no description.
     *
     * @throws EmptyDescriptionException The exception thrown when the task has empty description.
     */
    public static void checkEmptyDescription(String type, String description) throws EmptyDescriptionException {
        if (description.isBlank() || description.isEmpty()) {
            throw new EmptyDescriptionException(type);
        }
    }

    /**
     * Throws MissingArgumentException if the command does not specify its corresponding argument.
     *
     * @param command The command involved.
     * @param description The command description.
     * @param argument The argument.
     * @throws MissingArgumentException The exception thrown when there is missing argument in the command.
     */
    public static void checkMissingArgument(String command, String description, String argument)
            throws MissingArgumentException {
        boolean isMissingArgument = !(" " + description).contains(argument);
        if (isMissingArgument) {
            throw new MissingArgumentException(command, argument);
        }
    }

    /**
     * Throws IncompleteDescriptionException if the task description is incomplete.
     *
     * @param type The task type.
     * @param description The task description.
     * @param delimiter The delimiter used to split the description into left and right parts.
     * @throws IncompleteDescriptionException The exception thrown when the task description is incomplete.
     */
    public static void checkIncompleteDescription(String type, String description, String delimiter)
            throws IncompleteDescriptionException {
        String[] components = description.split(delimiter);
        boolean hasEmpty = components.length < 2;
        if (hasEmpty) {
            throw new IncompleteDescriptionException(type);
        }

        boolean hasBlank = components[0].isBlank() || components[1].isBlank();
        if (hasBlank) {
            throw new IncompleteDescriptionException(type);
        }
    }

    /**
     * Throws InvalidDateFormatException if the date string has invalid format.
     *
     * @param description The task description.
     * @param delimiter The delimiter used to split the description into left and right parts.
     * @throws InvalidDateFormatException The exception thrown when the date string has invalid format.
     */
    public static void checkInvalidDateFormat(String description, String delimiter)
            throws InvalidDateFormatException {
        boolean isInvalidDateFormat = !description.split(delimiter)[1].matches("\\d{4}-\\d{2}-\\d{2}");
        if (isInvalidDateFormat) {
            throw new InvalidDateFormatException(delimiter.substring(1, 4));
        }
    }

    /**
     * Throws DateParsingFailException if the date string cannot be parsed into LocalDate.
     *
     * @param description The task description.
     * @param delimiter The delimiter used to split the description into left and right parts.
     * @throws DateParsingFailException The exception thrown when the date string cannot be parsed into LocalDate.
     */
    public static void checkDateParsing(String description, String delimiter) throws DateParsingFailException {
        try {
            String dateString = description.split(delimiter)[1];
            LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DateParsingFailException(e.getMessage());
        }
    }

    /**
     * Throws InvalidDescriptionException if the description is not 'int' and not more than 0.
     *
     * @param description The task description.
     * @throws InvalidDescriptionException The exception thrown when the description is not 'int' and not more than 0.
     */
    public static void checkInvalidDescription(String description) throws InvalidDescriptionException {
        try {
            int index = Integer.parseInt(description);

            if (index <= 0) {
                throw new InvalidDescriptionException("Your index must be at least 1.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException(description + " is not an integer number!");
        } catch (InvalidDescriptionException e) {
            throw new InvalidDescriptionException(e.getMessage());
        }
    }

    /**
     * Throws TaskNotFoundException when the index is larger than the total task number.
     *
     * @throws TaskNotFoundException The exception thrown when the index is larger than the total task number
     */
    public static void checkTaskExistence(int index, int totalTaskNum) throws TaskNotFoundException {
        if (index > totalTaskNum) {
            throw new TaskNotFoundException(index, totalTaskNum);
        }
    }
}
