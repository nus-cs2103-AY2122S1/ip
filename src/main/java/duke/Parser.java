package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EmptyCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.command.UnexpectedCommand;
import duke.command.WelcomeCommand;

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
        case "welcome":
            output = new WelcomeCommand();
            break;
        case "todo":
            try {
                String toDoTask = splitText[1];
                output = new ToDoCommand(toDoTask);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                output = new UnexpectedCommand("array out of bounds exception, todo");
                break;
            }
        case "event":
            try {
                String[] furtherSplitEvent = splitText[1].trim().split("/at ");
                String eventTask = furtherSplitEvent[0];
                LocalDateTime eventTime = LocalDateTime.parse(furtherSplitEvent[1], Ui.TIME_FORMATTER);
                output = new EventCommand(eventTask, eventTime);
                break;
            } catch (DateTimeParseException e) {
                output = new UnexpectedCommand("date time parse exception");
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                output = new UnexpectedCommand("array out of bounds exception, event");
                break;
            }
        case "deadline":
            try {
                String[] furtherSplitDeadline = splitText[1].trim().split("/by ");
                String deadlineTask = furtherSplitDeadline[0];
                LocalDateTime deadlineBy = LocalDateTime.parse(furtherSplitDeadline[1], Ui.TIME_FORMATTER);
                output = new DeadlineCommand(deadlineTask, deadlineBy);
                break;
            } catch (DateTimeParseException e) {
                output = new UnexpectedCommand("date time parse exception");
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                output = new UnexpectedCommand("array out of bounds exception, deadline");
                break;
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
                output = new UnexpectedCommand("number format exception");
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                output = new UnexpectedCommand("array out of bounds exception");
                break;
            }
        case "delete":
            try {
                int index = Integer.parseInt(splitText[1]);
                output = new DeleteCommand(index);
                break;
            } catch (NumberFormatException e) {
                output = new UnexpectedCommand("number format exception");
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                output = new UnexpectedCommand("array out of bounds exception");
                break;
            }
        case "find":
            try {
                String keyword = splitText[1];
                output = new FindCommand(keyword);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                output = new UnexpectedCommand("array out of bounds exception, find");
                break;
            }
        case "bye":
            output = new ByeCommand();
            break;
        default:
            output = new UnexpectedCommand("");
            break;
        }
        return output;
    }
}
