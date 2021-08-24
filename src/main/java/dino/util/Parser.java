package dino.util;

import dino.command.*;
import dino.exception.*;

public class Parser {

    public static Command parse(String input) throws InvalidInputException {
        String type = getFirstWord(input);
        if (type.equals("todo") || type.equals("deadline") || type.equals("event")) {
            Command.CMDTYPE taskType = Command.CMDTYPE.valueOf(type.toUpperCase());
            return new AddTaskCommand(input, taskType);
        } else if (type.equals("done") || type.equals("delete")) {
            Command.CMDTYPE markType = Command.CMDTYPE.valueOf(type.toUpperCase());
            return new MarkCommand(input, markType);
        } else if (type.equals("list")) {
            return new ListCommand();
        } else {
            throw new InvalidInputException();
        }
    }

    public static String getFirstWord(String s) {
        String firstWord;
        if (s.contains(" ")) {
            firstWord = s.substring(0, s.indexOf(" "));
            return firstWord;
        }
        return s;
    }

}
