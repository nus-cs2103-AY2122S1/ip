package core;

import commands.*;

public class Parser {
    public static Command parse(String input) {
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
                return null;
        }
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

    // Add static method to check validity

}
