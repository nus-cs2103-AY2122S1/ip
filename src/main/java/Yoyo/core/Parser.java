package Yoyo.core;

import Yoyo.command.*;
import Yoyo.exception.YoyoException;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import static java.lang.Integer.parseInt;

public class Parser {

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
        default:
            throw new YoyoException.YoyoCommandNotFoundException("duke.Yoyo doesn't understand "
                    + "what you mean :-(");
        }
    }


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
