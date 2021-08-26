package duke;

import duke.command.*;

public class Parser {

    public static Command parse(String userInput) {
        String command = getCommand(userInput);
        switch (command) {
            case "todo":
                return new AddCommand(
                        getDescription(userInput, "todo", "ignore"),
                        "todo");
            case "deadline":
                return new AddCommand(
                        getDescription(userInput, "deadline ", "/by "),
                        getDate(userInput,"/by "),
                        "deadline"
                );
            case "event":
                return new AddCommand(
                        getDescription(userInput, "event ", "/at "),
                        getDate(userInput,"/at "),
                        getTime(userInput),
                        "event"
                );
            case "done":
                return new DoneCommand(getTaskNumber(userInput));
            case "delete":
                return new DeleteCommand(getTaskNumber(userInput));
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            default:
                return new InvalidCommand();
        }
    }

    private static String getCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    private static String getDescription(String userInput, String splitText, String splitTime) {
        String[] splitInput = userInput.split(splitText)[1].split(splitTime);
        return splitInput[0].trim();
    }

    private static String getDate(String userInput, String splitTime) {
        String[] splitInput = userInput.split(splitTime);
        String date = splitInput[1];
        if (date.split(" ").length > 1) {
            return date.split(" ")[0];
        }
        return date;
    }

    private static String getTime(String userInput) {
        String[] splitInput = userInput.split(" ");
        String time = splitInput[splitInput.length - 1];
        return time.substring(0,2) + ":" + time.substring(2);
    }

    private static int getTaskNumber(String userInput) {
        return Integer.parseInt(userInput.split(" ")[1]);
    }

}
