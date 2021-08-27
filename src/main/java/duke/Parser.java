package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Ui.Commands;
import duke.Ui.Descriptors;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;

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
        // Only exactly "list" will be accepted for ListCommand.
        if (userInput.equals(Commands.LIST.getCommand())) {
            // Print tasks
            return new ListCommand(userInput);
        } else {
            if (userInput.startsWith(Commands.DONE.getCommand())) {
                // Mark task as done.
                return new MarkCommand(userInput);
            } else if (userInput.startsWith(Commands.DELETE.getCommand())) {
                // Delete a task.
                return new DeleteCommand(userInput);
            } else if (userInput.startsWith(Commands.DATE.getCommand())) {
                // Print tasks that fall on given date.
                return new DateCommand(userInput);
            } else if (userInput.startsWith(Commands.FIND.getCommand())) {
                // Print tasks with descriptions that contain search input.
                return new FindCommand(userInput);
            } else {
                // Add a task to tasks.
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
     * @param command The starting command of user's input.
     * @param exceptionMessage Error message to be printed when user input is invalid.
     * @throws DukeException If user command is invalid.
     * @throws DukeException If user input contains missing spaces.
     */
    public static void checkInputValidity(String userInput, String command,
            String exceptionMessage) throws DukeException {

        // If userInput is <= command length, it definitely does not contain desired inputs.
        if (userInput.length() <= command.length()) {
            throw new DukeException(exceptionMessage);
        }

        // If userInput after command is not space, tell users that they are missing space.
        if (userInput.charAt(command.length()) != ' ') {
            throw new DukeException(Ui.exceptionMissingSpaceAfterCommand(command));
        } else {
            // If user input is like so "command ", it also does not contain desired inputs, despite having space.
            if (userInput.length() == (command.length() + 1)) {
                throw new DukeException(exceptionMessage);
            }
        }
    }

    /**
     * Prepends zeroes to the string till a desired length of the output string.
     *
     * @param original String to prepend zeroes to.
     * @param expected Length of output string.
     * @return String prepended with zeroes till desired length.
     * @throws DukeException If string input is larger than expected length of output string.
     */
    private static String padZeros(String original, int expected) throws DukeException {
        String output = original;
        if (original.length() < expected) {
            for (int i = 0; i < (expected - original.length()); i++) {
                output = "0" + original;
            }
        } else if (original.length() > expected) {
            throw new DukeException(Ui.exceptionInvalidDateTimeFormat());
        }

        return output;
    }

    /**
     * Parses a LocalDate into a string format "d MMMM yyyy" to be printed to the user.
     * For instance, the LocalDate equivalent of 2020-01-01 will be parsed into "1 January 2020".
     *
     * @param localDate LocalDate to be parsed.
     * @return Formatted string representing the date of LocalDate in d MMMM yyyy format.
     * @throws DukeException
     */
    public static String parseLocalDate(LocalDate localDate) throws DukeException {
        try {
            return localDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        } catch (DateTimeException dateTimeException) {
            throw new DukeException(Ui.exceptionInvalidLocalDate());
        }
    }

    /**
     * Parses a formatted string into LocalDate.
     * The formatted string must be of the format "dd/mm/yyyy".
     *
     * @param dateString Formatted string to be parsed.
     * @return LocalDate equivalent of the date represented by dateString.
     * @throws DukeException If dateString is of an invalid date format.
     * @throws DukeException If dateString represents a date later than "+999999999-12-31" or
     *                       earlier than "-999999999-01-01".
     */
    public static LocalDate toLocalDate(String dateString) throws DukeException {
        String[] split = dateString.split("[/\\s]");

        // Check if split has 3 elements. If not, this is already an invalid date format.
        if (split.length != 3) {
            throw new DukeException(Ui.exceptionInvalidDateTimeFormat());
        }

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
            throw new DukeException(Ui.exceptionInvalidDateTimeFormat());
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
     * @throws DukeException If user input for index cannot be parsed into Integer.
     */
    public static int parseUserNumInput(String userInput, Commands command) throws DukeException {
        // Parses integer in user input. Invalid user input could throw NumberFormatException.
        try {
            // Add 1 as user's number input is separated from command by 1 space.
            return Integer.parseInt(userInput.substring(command.getLength() + 1));
        } catch (NumberFormatException nfe) {
            // Invalid user input cannot be parsed into Integer.
            throw new DukeException(Ui.exceptionInvalidNumberInput(command));
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
     * @param command The initial command in user's input.
     * @return String array of 2 elements with the task description at index 0 and time at index 1.
     * @throws DukeException If user input is missing the expected descriptor or a time input.
     * @throws DukeException If user input is missing a task description.
     * @throws DukeException If user input is using the wrong descriptor.
     * @throws DukeException If user input has missing spaces.
     */
    public static String[] parseUserDescriptionInput(String userDescription, Descriptors descriptor,
                                                      char separator, Commands command) throws DukeException {
        // Index of separator in userDescription.
        int separatorIdx = findIndex(userDescription, separator);

        // Index of space after descriptor
        int indexDescriptorSpace = separatorIdx + descriptor.getLength() + 1;

        // Check if separator exists. Then check if there could be time input after descriptor.
        if ((separatorIdx == -1) || (userDescription.length() <= indexDescriptorSpace)) {
            throw new DukeException(Ui.exceptionMissingDescriptor(descriptor, command));
        }

        // Events and duke.task.Deadline could have empty tasks but taken as they do due to their descriptors and time.
        // Need to run another check on whether their task descriptions are empty.
        if (separatorIdx == 0) {
            throw new DukeException(Ui.exceptionMissingTaskDescription(command.getCommand()));
        }

        // Index of first character following space after descriptor.
        int indexAfterDescriptorSpace = separatorIdx + descriptor.getLength() + 2;

        // Check if descriptor matches descriptor parameter.
        String actualDescriptor = userDescription.substring(separatorIdx + 1, indexAfterDescriptorSpace - 1);
        boolean isDescriptorIncorrect = false;
        if (!actualDescriptor.equals(descriptor.getDescriptor())) {
            isDescriptorIncorrect = true;
        }

        // Check whether the front of separatorIdx is an empty space.
        boolean isNoSpaceBeforeSeparator = false;
        if (userDescription.charAt(separatorIdx - 1) != ' ') {
            isNoSpaceBeforeSeparator = true;
        }

        // If descriptor wrong and no space before separator, it is likely that user did not provide descriptor.
        if (isDescriptorIncorrect && isNoSpaceBeforeSeparator) {
            throw new DukeException(Ui.exceptionMissingDescriptor(descriptor, command));
        }

        // If only descriptor wrong, user gave wrong descriptor.
        if (isDescriptorIncorrect) {
            throw new DukeException(Ui.exceptionWrongDescriptor(command, descriptor));
        }

        // If only no space, user did not include space before separator.
        if (isNoSpaceBeforeSeparator) {
            throw new DukeException(Ui.exceptionMissingSpaceBeforeDescriptor(descriptor));
        }

        // Check whether the back of descriptor is followed by a space.
        if (userDescription.charAt(indexAfterDescriptorSpace - 1) != ' ') {
            throw new DukeException(Ui.exceptionMissingSpaceAfterDescriptor(descriptor));
        }

        // User's time input.
        String time = userDescription.substring(indexAfterDescriptorSpace);

        // User's task description. Decrement by 1 as there is a space between task description and separator
        String commandDescription = userDescription.substring(0, separatorIdx - 1);

        // Returns a String array with the task description and user input after descriptor.
        return new String[] {commandDescription, time};
    }
}
