package main.java;

/**
 * Parser reads the input by the user and returns the relevant command.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class Parser {

    /**
     * Reads the fullCommand and return the relevant command.
     *
     * @param fullCommand the fullCommand
     * @return the relevant command
     */
    protected static Command parse(String fullCommand) {
        String[] commandPair = fullCommand.split(" ", 2);
        String description = commandPair.length == 1 ? "" : commandPair[1];
        switch (commandPair[0]) {
            case "bye":
                return new Bye(description);
            case "list":
                return new List(description);
            case "done":
                return new Done(description);
            case "delete":
                return new Delete(description);
            case "todo":
                return new AddTodo(description);
            case "deadline":
                return new AddDeadline(description);
            case "event":
                return new AddEvent(description);
            default:
                return new Invalid(description);
        }
    }


}
