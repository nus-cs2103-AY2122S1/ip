package duke;

import duke.Ui.Commands;
import duke.Ui.Descriptors;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deals with making sense of user commands and inputs.
 */
public class Parser {

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

    public static String parseLocalDate(LocalDate localDate) throws DukeException {
        try {
            return localDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        } catch (DateTimeException dateTimeException) {
            throw new DukeException(Ui.exceptionInvalidLocalDate());
        }
    }

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
