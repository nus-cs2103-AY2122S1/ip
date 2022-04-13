package yoyo.core;

import static java.lang.Integer.parseInt;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import yoyo.command.Command;
import yoyo.command.CommandBye;
import yoyo.command.CommandDeadline;
import yoyo.command.CommandDelete;
import yoyo.command.CommandDone;
import yoyo.command.CommandEvent;
import yoyo.command.CommandFind;
import yoyo.command.CommandList;
import yoyo.command.CommandTag;
import yoyo.command.CommandTodo;
import yoyo.exception.YoyoException;

/**
 * Parser class that represents the parser component of Yoyo program.
 */
public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_TAG = "tag";

    /**
     * Reads an input string and returns the appropriate Command.
     *
     * @param fullCommand Full command that has been entered by user.
     * @return a Command instance matching the user input.
     * @throws YoyoException
     */
    public static Command parse(String fullCommand) throws YoyoException {
        fullCommand = fullCommand.trim();
        if (fullCommand.length() == 0) {
            throw new YoyoException.YoyoEmptyCommandException("You have not entered a command!");
        }

        String[] inputTokens = fullCommand.split(" ", 2);
        String commandKeyword = inputTokens[0].toLowerCase();

        switch (commandKeyword) {
        case COMMAND_BYE:
            return new CommandBye(inputTokens);
        case COMMAND_LIST:
            return new CommandList(inputTokens);
        case COMMAND_DONE:
            return new CommandDone(inputTokens);
        case COMMAND_DELETE:
            return new CommandDelete(inputTokens);
        case COMMAND_TODO:
            return new CommandTodo(inputTokens);
        case COMMAND_DEADLINE:
            return new CommandDeadline(inputTokens);
        case COMMAND_EVENT:
            return new CommandEvent(inputTokens);
        case COMMAND_FIND:
            return new CommandFind(inputTokens);
        case COMMAND_TAG:
            return new CommandTag(inputTokens);
        default:
            assertCommandNotMissed(commandKeyword);
            throw new YoyoException.YoyoCommandNotFoundException("Yoyo doesn't understand "
                    + "what you mean :-(");
        }
    }

    /**
     * Reads an input string and returns the appropriate LocalDateTime.
     *
     * @param timeString Time string to be parsed.
     * @return a LocalDateTime instance matching input.
     * @throws YoyoException
     */
    public static LocalDateTime parseTimeString(String timeString)
            throws YoyoException {
        char separator = identifySeparator(timeString);


        String[] dateTimeArr = timeString.split(" ");
        Command.checkTwoTokenCommand(dateTimeArr);
        String[] dateArr = dateTimeArr[0].split(String.valueOf(separator));
        String hourMinuteString = dateTimeArr[1];

        /*
        dateArr should have exactly 3 values for year, month and day
        hourMinuteString uses 'hhmm' format and should not be less than 3 characters
         */
        if (dateArr.length != 3 || hourMinuteString.length() < 3) {
            throw new YoyoException.YoyoInvalidFormatException("Invalid datetime format, "
                    + "use 'yyyy/MM/dd hhmm'");
        }

        return getLocalDateTime(dateArr, hourMinuteString);
    }

    private static void assertCommandNotMissed(String commandKeyword) {
        assert !commandKeyword.equals("bye") : "Did not catch valid command";
        assert !commandKeyword.equals("list") : "Did not catch valid command";
        assert !commandKeyword.equals("done") : "Did not catch valid command";
        assert !commandKeyword.equals("delete") : "Did not catch valid command";
        assert !commandKeyword.equals("todo") : "Did not catch valid command";
        assert !commandKeyword.equals("deadline") : "Did not catch valid command";
        assert !commandKeyword.equals("event") : "Did not catch valid command";
        assert !commandKeyword.equals("find") : "Did not catch valid command";
        assert !commandKeyword.equals("tag") : "Did not catch valid command";
    }

    private static LocalDateTime getLocalDateTime(String[] dateArr, String time)
            throws YoyoException.YoyoInvalidFormatException {
        LocalDateTime result;

        int year = parseInt(dateArr[0]);
        int month = parseInt(dateArr[1]);
        int day = parseInt(dateArr[2]);
        int hour = parseInt(time.substring(0, 2));
        int min = parseInt(time.substring(2));

        try {
            result = LocalDateTime.of(year, month, day, hour, min);
        } catch (DateTimeException e) {
            throw new YoyoException.YoyoInvalidFormatException("Invalid datetime format, "
                    + "use 'yyyy/MM/dd hhmm'");
        }
        return result;
    }

    private static char identifySeparator(String timeString) throws YoyoException.YoyoInvalidFormatException {
        char separator;
        if (timeString.indexOf('/') != -1) {
            separator = '/';
        } else if (timeString.indexOf('-') != -1) {
            separator = '-';
        } else {
            throw new YoyoException.YoyoInvalidFormatException("Invalid datetime format, "
                    + "use 'yyyy/MM/dd hhmm'");
        }
        return separator;
    }

}
