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
import yoyo.command.CommandTodo;
import yoyo.exception.YoyoException;

public class Parser {

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
        String commandKeyword = inputTokens[0];
        switch (commandKeyword) {
        case "bye":
            return new CommandBye(inputTokens);
        case "list":
            return new CommandList(inputTokens);
        case "done":
            return new CommandDone(inputTokens);
        case "delete":
            return new CommandDelete(inputTokens);
        case "todo":
            return new CommandTodo(inputTokens);
        case "deadline":
            return new CommandDeadline(inputTokens);
        case "event":
            return new CommandEvent(inputTokens);
        case "find":
            return new CommandFind(inputTokens);
        default:
            assertCommandNotMissed(commandKeyword);
            throw new YoyoException.YoyoCommandNotFoundException("Yoyo doesn't understand "
                    + "what you mean :-(");
        }
    }

    /**
     * Checks by using assertion that valid commands are not missed by the program.
     *
     * @param commandKeyword Command entered by user.
     */
    private static void assertCommandNotMissed(String commandKeyword) {
        assert !commandKeyword.equals("bye") : "Did not catch valid command";
        assert !commandKeyword.equals("list") : "Did not catch valid command";
        assert !commandKeyword.equals("done") : "Did not catch valid command";
        assert !commandKeyword.equals("delete") : "Did not catch valid command";
        assert !commandKeyword.equals("todo") : "Did not catch valid command";
        assert !commandKeyword.equals("deadline") : "Did not catch valid command";
        assert !commandKeyword.equals("event") : "Did not catch valid command";
        assert !commandKeyword.equals("find") : "Did not catch valid command";
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
        char separator;
        if (timeString.indexOf('/') != -1) {
            separator = '/';
        } else if (timeString.indexOf('-') != -1) {
            separator = '-';
        } else {
            throw new YoyoException.YoyoInvalidFormatException("Invalid datetime format, "
                    + "use 'yyyy/MM/dd hhmm'");
        }


        String[] dateTimeArr = timeString.split(" ");
        Command.checkCompleteCommand(dateTimeArr);
        String[] dateArr = dateTimeArr[0].split(String.valueOf(separator));

        /*
        dateArr should have exactly 3 values for year, month and day
        dateTimeArr[1] contains 'hhmm' and should not be less than 3 characters
         */
        if (dateArr.length != 3 || dateTimeArr[1].length() < 3) {
            throw new YoyoException.YoyoInvalidFormatException("Invalid datetime format, "
                    + "use 'yyyy/MM/dd hhmm'");
        }

        int year = parseInt(dateArr[0]);
        int month = parseInt(dateArr[1]);
        int day = parseInt(dateArr[2]);
        int hour = parseInt(dateTimeArr[1].substring(0, 2));
        int min = parseInt(dateTimeArr[1].substring(2));
        LocalDateTime result;
        try {
            result = LocalDateTime.of(year, month, day, hour, min);
        } catch (DateTimeException e) {
            throw new YoyoException.YoyoInvalidFormatException("Invalid datetime format, "
                    + "use 'yyyy/MM/dd hhmm'");
        }
        return result;
    }

}
