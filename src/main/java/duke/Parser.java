package duke;

import duke.command.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * This class represents a Parser, which parses the input given by the user.
 */
public class Parser {

    /**
     * Parses the input given by the user and returns the necessary command to be executed.
     *
     * @param input input given by user.
     * @return necessary command to be executed.
     */
    public static Command parse(String input) {
        String[] splitText = input.trim().split("\\s+", 2);
        String command = splitText[0];
        Command output;
        output = new EmptyCommand();
        switch (command) {
        case "todo":
            try {
                String toDoTask = splitText[1];
                output = new ToDoCommand(toDoTask);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.arrayIndexOutOfBoundsExceptionMessage();
                Ui.toDoHint();
                throw e;
            }
        case "event":
            try {
                String[] furtherSplitEvent = splitText[1].trim().split("/at ");
                String eventTask = furtherSplitEvent[0];
                LocalDateTime eventTime = LocalDateTime.parse(furtherSplitEvent[1], Ui.TIME_FORMATTER);
                output = new EventCommand(eventTask, eventTime);
                break;
            } catch (DateTimeParseException e) {
                Ui.dateTimeParseExceptionMessage();
                throw e;
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.arrayIndexOutOfBoundsExceptionMessage();
                Ui.eventHint();
                throw e;
            }
        case "deadline":
            try {
                String[] furtherSplitDeadline = splitText[1].trim().split("/by ");
                String deadlineTask = furtherSplitDeadline[0];
                LocalDateTime deadlineBy = LocalDateTime.parse(furtherSplitDeadline[1], Ui.TIME_FORMATTER);
                output = new DeadlineCommand(deadlineTask, deadlineBy);
                break;
            } catch (DateTimeParseException e) {
                Ui.dateTimeParseExceptionMessage();
                throw e;
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.arrayIndexOutOfBoundsExceptionMessage();
                Ui.deadlineHint();
                throw e;
            }
        case "list":
            output = new ListCommand();
            break;
        case "done":
            try {
                int index = Integer.parseInt(splitText[1]);
                output = new DoneCommand(index);
                break;
            } catch (NumberFormatException e) {
                Ui.numberFormatExceptionMessage();
                throw e;
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.arrayIndexOutOfBoundsExceptionMessage();
                throw e;
            }
        case "delete":
            try {
                int index = Integer.parseInt(splitText[1]);
                output = new DeleteCommand(index);
                break;
            } catch (NumberFormatException e) {
                Ui.numberFormatExceptionMessage();
                throw e;
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.arrayIndexOutOfBoundsExceptionMessage();
                throw e;
            }
        case "bye":
            output = new ByeCommand();
            break;
        default:
            Ui.defaultMessage();
            break;
        }
        return output;

    }
}
