package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Ui.Descriptors;
import duke.Ui.UserCommands;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeFormatException;
import duke.exception.InvalidLocalDateException;
import duke.exception.InvalidNumberInputException;
import duke.exception.InvalidPeriodException;
import duke.exception.MissingDateException;
import duke.exception.MissingSpaceAfterCommandException;
import duke.exception.MissingSpaceAfterDescriptorException;
import duke.exception.MissingSpaceBeforeDescriptorException;
import duke.exception.MissingTaskDescriptionException;
import duke.exception.WrongDescriptorException;

/**
 * Deals with making sense of user commands and inputs.
 */
public class Parser {

    /**
     * Reads user input and returns the corresponding Command.
     *
     * @param userInput User's input into Duke chatbot.
     * @return Command that can be executed to perform a set of actions.
     */
    public static Command parse(String userInput) {
        if (userInput.equals(UserCommands.LIST.getCommand())) {
            return new ListCommand(userInput);
        } else {
            if (userInput.startsWith(UserCommands.DONE.getCommand())) {
                return new MarkCommand(userInput);
            } else if (userInput.startsWith(UserCommands.DELETE.getCommand())) {
                return new DeleteCommand(userInput);
            } else if (userInput.startsWith(UserCommands.DATE.getCommand())) {
                return new DateCommand(userInput);
            } else if (userInput.startsWith(UserCommands.FIND.getCommand())) {
                return new FindCommand(userInput);
            } else {
                return new AddCommand(userInput);
            }
        }
    }

    /**
     * Checks user input for missing spaces and invalid commands.
     * This only checks for missing spaces in initial command and if the initial command is invalid.
     * For instance, in "event task description/at 1/1/2020", it only checks if there is a space following
     * the initial command "event" and if the initial command "event" is a valid command.
     *
     * @param userInput User's input into Duke chatbot.
     * @param userCommand The starting command of user's input.
     * @param exception The exception to be thrown if input is invalid.
     * @throws MissingSpaceAfterCommandException If user input contains missing spaces.
     * @throws DukeException If user input is invalid.
     */
    public static void checkInputValidity(String userInput, UserCommands userCommand,
            DukeException exception) throws DukeException {
        String command = userCommand.getCommand();

        // If userInput is <= command length, it definitely does not contain desired inputs.
        if (userInput.length() <= command.length()) {
            throw exception;
        }

        // If userInput after command is not space, tell users that they are missing space.
        if (userInput.charAt(command.length()) != ' ') {
            throw new MissingSpaceAfterCommandException(userCommand);
        } else {
            // If user input is like so "command ", it also does not contain desired inputs, despite having space.
            if (userInput.length() == (command.length() + 1)) {
                throw exception;
            }
        }
    }

    /**
     * Prepends zeroes to the string till a desired length of the output string.
     *
     * @param original String to prepend zeroes to.
     * @param expected Length of output string.
     * @return String prepended with zeroes till desired length.
     * @throws InvalidDateTimeFormatException If string input is larger than expected length of output string.
     */
    private static String padZeros(String original, int expected) throws InvalidDateTimeFormatException {
        String output = original;
        if (original.length() < expected) {
            // Number of chars needed to meet desired length of output string.
            int missingCount = expected - original.length();
            for (int i = 0; i < missingCount; i++) {
                output = "0" + original;
            }
        } else if (original.length() > expected) {
            throw new InvalidDateTimeFormatException();
        }

        return output;
    }

    /**
     * Parses a LocalDate into a string format "d MMMM yyyy" to be displayed to the user.
     * For instance, the LocalDate equivalent of 2020-01-01 will be parsed into "1 January 2020".
     *
     * @param localDate LocalDate to be parsed.
     * @return Formatted string representing the date of LocalDate in d MMMM yyyy format.
     * @throws InvalidLocalDateException If LocalDate cannot be parsed.
     */
    public static String parseLocalDate(LocalDate localDate) throws InvalidLocalDateException {
        try {
            return localDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        } catch (DateTimeException dateTimeException) {
            throw new InvalidLocalDateException();
        }
    }

    /**
     * Parses a formatted string into LocalDate.
     * The formatted string must be of the format "dd/mm/yyyy".
     *
     * @param dateString Formatted string to be parsed.
     * @return LocalDate equivalent of the date represented by dateString.
     * @throws InvalidDateTimeFormatException If dateString is of an invalid date format.
     * @throws InvalidDateTimeFormatException If dateString represents a date later than "+999999999-12-31" or
     *                       earlier than "-999999999-01-01".
     */
    public static LocalDate dateToLocalDate(String dateString) throws InvalidDateTimeFormatException {
        String[] split = dateString.split("[/\\s]");

        // Check if split has 3 elements. If not, this is already an invalid date format.
        if (split.length != 3) {
            throw new InvalidDateTimeFormatException();
        }

        // Convert user date input into dd-mm-yyyy format
        String date = padZeros(split[0], 2);
        String month = padZeros(split[1], 2);
        String year = padZeros(split[2], 4);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(year).append("-");
        stringBuilder.append(month).append("-");
        stringBuilder.append(date);

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(stringBuilder.toString());
        } catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidDateTimeFormatException();
        }

        return localDate;
    }

    /**
     * Parses index provided in user input into an int.
     * For instance, when user inputs "done 1", calling parseUserNumInput on this input will return 1.
     * In this case, the input's identifying command is "done".
     *
     * @param userInput User's input into Duke chatbot.
     * @param command The identifying command in user input.
     * @return An int representing an index.
     * @throws InvalidNumberInputException If user input for index cannot be parsed into Integer.
     */
    public static int parseUserNumInput(String userInput, UserCommands command) throws InvalidNumberInputException {
        try {
            // Add 1 as user's number input is separated from command by 1 space.
            return Integer.parseInt(userInput.substring(command.getLength() + 1));
        } catch (NumberFormatException nfe) {
            throw new InvalidNumberInputException(command);
        }
    }

    private static int findIndex(String s, Character c) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Parses user description into description and time based on initial command and descriptor used.
     * User description is defined to be the part of user's input following the initial command.
     * For instance, the user description of "event task description /at 1/1/2020" is
     * "task description /at 1/1/2020", where the initial command "event" and the space after it is removed.
     * An Event would require descriptor "at" whereas a Deadline would require descriptor "by".
     * The separator is a char that comes before the descriptor.
     * For instance, '/' is the separator in "/at".
     * Calling parseUserDescriptionInput on the example description will give a String array
     * of 2 elements ["task description", "1/1/2020"].
     *
     * @param userDescription User's input into Duke chatbot.
     * @param descriptor Descriptor to separate description and time with.
     * @param separator char that comes before the descriptor.
     * @param userCommand The initial command in user's input.
     * @return String array of 2 elements with the task description at index 0 and time at index 1.
     * @throws MissingDateException If user input is missing a date.
     * @throws MissingTaskDescriptionException If user input is missing a task description.
     * @throws WrongDescriptorException If user input is using the wrong descriptor.
     * @throws MissingSpaceBeforeDescriptorException If user input has missing space before descriptor.
     * @throws MissingSpaceAfterDescriptorException If user input has missing space after descriptor.
     */
    public static String[] parseUserDescriptionInput(String userDescription, Descriptors descriptor,
                                                      char separator, UserCommands userCommand) throws DukeException {
        // Index of separator in userDescription.
        int separatorIdx = findIndex(userDescription, separator);

        // Events and duke.task.Deadline could have empty tasks but taken as they do due to their descriptors and time.
        // Need to run another check on whether their task descriptions are empty.
        if (separatorIdx == 0) {
            throw new MissingTaskDescriptionException(userCommand);
        }

        // Index of space after descriptor
        int indexDescriptorSpace = separatorIdx + descriptor.getLength() + 1;

        // Check if separator exists. Then check if there could be time input after descriptor.
        if ((separatorIdx == -1) || (userDescription.length() <= indexDescriptorSpace)) {
            throw new MissingDateException();
        }

        // Index of first character following space after descriptor.
        int indexAfterDescriptorSpace = indexDescriptorSpace + 1;

        // Check if descriptor matches descriptor parameter.
        String actualDescriptor = userDescription.substring(separatorIdx + 1, indexDescriptorSpace);
        if (!actualDescriptor.equals(descriptor.getDescriptor())) {
            throw new WrongDescriptorException(descriptor, userCommand);
        }

        checkSeparatorSpaceFormatting(userDescription, descriptor, separatorIdx, indexDescriptorSpace);

        // User's time input.
        String time = userDescription.substring(indexAfterDescriptorSpace);

        // User's task description. Decrement by 1 as there is a space between task description and separator
        String taskDescription = userDescription.substring(0, separatorIdx - 1);

        // If task description is just whitespace, it is considered empty too.
        if (taskDescription.isBlank()) {
            throw new MissingTaskDescriptionException(userCommand);
        }

        // Returns a String array with the task description and user input after descriptor.
        return new String[] {taskDescription, time};
    }

    private static void checkSeparatorSpaceFormatting(String userDescription, Descriptors descriptor,
            int separatorIdx, int indexDescriptorSpace) throws MissingSpaceAfterDescriptorException,
                    MissingSpaceBeforeDescriptorException {
        // Check whether the front of separatorIdx is an empty space.
        if (userDescription.charAt(separatorIdx - 1) != ' ') {
            throw new MissingSpaceBeforeDescriptorException(descriptor);
        }

        // Check whether the back of descriptor is followed by a space.
        if (userDescription.charAt(indexDescriptorSpace) != ' ') {
            throw new MissingSpaceAfterDescriptorException(descriptor);
        }
    }

    /**
     * Splits userTimePeriodInput into a 2 element LocalDate array containing startDate and endDate.
     *
     * @return LocalDate Array containing startDate and endDate.
     * @throws MissingDateException If user's time period input cannot be split into 2 by a single space.
     * @throws InvalidPeriodException If user's time period is chronologically invalid.
     * @throws DukeException If underlying methods fail.
     */
    public static LocalDate[] periodToLocalDate(String userTimePeriodInput) throws DukeException {
        String[] split = userTimePeriodInput.split(" ");

        // If userTimePeriodInput cannot be split into 2, it is assumed that user did not provide sufficient dates
        if (split.length != 2) {
            throw new MissingDateException();
        }

        LocalDate startDate = dateToLocalDate(split[0]);
        LocalDate endDate = dateToLocalDate(split[1]);

        if (endDate.compareTo(startDate) < 0) {
            throw new InvalidPeriodException(split[0], split[1]);
        }

        return new LocalDate[]{startDate, endDate};
    }

}
