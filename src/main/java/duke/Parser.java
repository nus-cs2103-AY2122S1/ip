package duke;

import duke.Ui.Commands;
import duke.Ui.Descriptors;

import duke.command.Command;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.DeleteCommand;
import duke.command.DateCommand;
import duke.command.AddCommand;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deals with making sense of user commands and inputs.
 */
public class Parser {

    public static Command parse(String userInput) {
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
            } else {
                // Add a task to tasks.
                return new AddCommand(userInput);
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
                                                      Character separator, Commands command) throws DukeException {
        // Index of separator in userDescription.
        int separatorIdx = findIndex(userDescription, separator);

        // Index of space after descriptor
        int indexDescriptorSpace = separatorIdx + descriptor.getLength() + 1;
        if ((separatorIdx == -1) || (userDescription.length() <= indexDescriptorSpace)) {
            throw new DukeException(Ui.exceptionMissingDescriptor(descriptor, command));
        }

        // Index of first character following space after descriptor.
        int indexAfterDescriptorSpace = separatorIdx + descriptor.getLength() + 2;

        // User's time input.
        String time = userDescription.substring(indexAfterDescriptorSpace);

        // User's task description. Decrement by 1 as there is a space between task description and separator
        String commandDescription = userDescription.substring(0, separatorIdx - 1);

        // Events and duke.task.Deadline could have empty tasks but taken as they do due to their descriptors and time.
        // Need to run another check on whether their task descriptions are empty.
        if (commandDescription.equals("")) {
            throw new DukeException(Ui.exceptionMissingTaskDescription(command.getCommand()));
        }

        // Returns a String array with the task description and user input after descriptor.
        return new String[] {commandDescription, time};
    }
}
