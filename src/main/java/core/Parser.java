package core;

import commands.*;

public class Parser {
    public static Command parse(String input) {
        try {
            checkValiditiy(input);
            String[] splittedInput = input.split(" ");
            switch (splittedInput[0]) {
                case "list":
                    return new ListCommand();
                case "done":
                    return new DoneCommand(Integer.valueOf(splittedInput[1]));
                case "bye":
                    return new ByeCommand();
                case "todo":
                    return Parser.todoHandler(input);
                case "deadline":
                    return Parser.deadlineHandler(input);
                case "event":
                    return Parser.eventHandler(input);
                default:
                    // This is for any other erroneous input we did not catch from checkValidity
                    DukeException e = new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    return new ExceptionalCommand(e);
            }
        } catch (DukeException e) {
            return new ExceptionalCommand(e);
        }

    }

    public static boolean checkValiditiy(String input) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (input.length() == 0) {
            throw new DukeException("☹ OOPS!!! The input cannot be empty.");
        } else if (splittedInput.length == 1) {
            switch (splittedInput[0]) {
                case "todo":
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                case "deadline":
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                case "event":
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        }

        return true;
    }

    public static Command todoHandler(String input) {
        String[] splittedInput = input.split(" ", 2);
        return new TodoCommand(splittedInput[1]);
    }

    public static Command deadlineHandler(String input) {
        String details = input.split(" ", 2)[1];
        String regexForDetails = "/by";
        String splittedDetails[] = details.split(regexForDetails);
        return new DeadlineCommand(splittedDetails[0].trim(), splittedDetails[1].trim());
    }

    public static Command eventHandler(String input) {
        String details = input.split(" ", 2)[1];
        String regexForDetails = "/at";
        String splittedDetails[] = details.split(regexForDetails);
        return new EventCommand(splittedDetails[0].trim(), splittedDetails[1].trim());
    }

}
