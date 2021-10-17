package bloom.app;

import java.time.LocalDateTime;

import bloom.command.Command;
import bloom.command.DeadlineCommand;
import bloom.command.DeleteCommand;
import bloom.command.EventCommand;
import bloom.command.ExitCommand;
import bloom.command.FindCommand;
import bloom.command.GreetCommand;
import bloom.command.ListCommand;
import bloom.command.MarkCommand;
import bloom.command.NoteCommand;
import bloom.command.ToDoCommand;
import bloom.constant.Message;
import bloom.exception.command.BloomUnknownCommandException;

/**
 * Takes in an input string and parse it into necessary format.
 */
public class Parser {

    /**
     * Matches the user input with correct command and format.
     *
     * @param userInput the text input received from users
     * @return          the respective command based on the first word
     * @throws BloomUnknownCommandException when an unsupported command is inputted
     */
    public Command parse(String userInput) throws BloomUnknownCommandException {
        String[] parse = userInput.split(" ");
        String action = parse[0];
        int descIdx;
        int dateIdx;
        switch (action) {
        case "greet":
            return new GreetCommand();
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(parse[1]);
        case "done":
            return new MarkCommand(Integer.parseInt(parse[1]) - 1);
        case "delete":
            return new DeleteCommand(Integer.parseInt(parse[1]) - 1);
        case "note":
            descIdx = action.length() + 3;
            return new NoteCommand(Integer.parseInt(parse[1]) - 1, userInput.substring(descIdx));
        case "todo":
            try {
                descIdx = action.length() + 1;
                return new ToDoCommand(userInput.substring(descIdx));
            } catch (Exception e) {
                throw new BloomUnknownCommandException(
                        Message.EXCEPTION_WRONG_FORMAT_TODO_COMMAND.getMessage());
            }
        case "deadline":
            try {
                descIdx = action.length() + 1;
                dateIdx = userInput.indexOf("/");
                return new DeadlineCommand(
                        userInput.substring(descIdx, dateIdx - 1),
                        new Parser().parseDate(userInput.substring(dateIdx + 4)));
            } catch (Exception e) {
                throw new BloomUnknownCommandException(
                        Message.EXCEPTION_WRONG_FORMAT_DEADLINE_COMMAND.getMessage());
            }
        case "event":
            try {
                descIdx = action.length() + 1;
                dateIdx = userInput.indexOf("/");
                return new EventCommand(
                        userInput.substring(descIdx, dateIdx - 1),
                        new Parser().parseDate(userInput.substring(dateIdx + 4)));
            } catch (Exception e) {
                throw new BloomUnknownCommandException(
                        Message.EXCEPTION_WRONG_FORMAT_EVENT_COMMAND.getMessage());
            }
        default:
            throw new BloomUnknownCommandException(
                    Message.EXCEPTION_UNKNOWN_COMMAND.getMessage());
        }
    }

    /**
     * Matches the date input with correct format.
     *
     * @param dateInput the date input
     * @return          the respective object containing date and time as inputted
     */
    public LocalDateTime parseDate (String dateInput) throws BloomUnknownCommandException {

        String[] parse = dateInput.split(" ");

        String date = parse[0];
        String time = parse[1];

        int[] separators = new int[2];
        char separator;
        if (dateInput.contains("-")) {
            separator = '-';
        } else { // assumes separator can be '-' or '/' only
            separator = '/';
        }
        separators[0] = date.indexOf(separator);
        separators[1] = date.indexOf(separator, separators[0] + 1);
        int year = Integer.parseInt(date.substring(separators[1] + 1));
        int month = Integer.parseInt(date.substring(
                separators[0] + 1, separators[1]));
        int day = Integer.parseInt(date.substring(0, separators[0]));

        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));
        int second = Integer.parseInt(time.length() == 6
                ? time.substring(4, 6) : "00");

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}
