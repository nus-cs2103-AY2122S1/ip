package duke.command;

/**
 * This class handles the making sense of user commands.
 */
public class Parser {
    /**
     * Takes in user input and performs an action accordingly.
     *
     * @param input The user's input.
     * @return The string representing the command interpreted.
     */
    public String interpretCommand(String input) {
        assert input != null : "User input cannot be null!";
        if (input.equalsIgnoreCase("list")) {
            return "list";
        } else if (input.length() >= 6 && input.substring(0, 4).toLowerCase().contains("done")) {
            return "mark";
        } else if (input.length() >= 8 && input.substring(0, 6).toLowerCase().contains("delete")) {
            return "delete";
        } else if (input.length() >= 6 && input.substring(0, 4).toLowerCase().contains("todo")) {
            return "todo";
        } else if (input.length() >= 10 && input.substring(0, 8).toLowerCase().contains("deadline")) {
            return "deadline";
        } else if (input.length() >= 7 && input.substring(0, 5).toLowerCase().contains("event")) {
            return "event";
        } else if (input.length() >= 6 && input.substring(0, 4).toLowerCase().contains("find")) {
            return "find";
        } else if (input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("help")) {
            return "help";
        } else if (input.length() >= 3 && input.substring(0, 3).equalsIgnoreCase("bye")) {
            return "bye";
        } else {
            return "invalid";
        }
    }
}
