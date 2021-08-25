package duke.command;

import duke.exception.InvalidCommandException;

/**
 * This class handles the making sense of user commands.
 */
public class Parser {

    public Parser() {

    }

    /**
     * Takes in user input and performs an action accordingly.
     */
    public String interpretCommand(String input) {
        if (input.equalsIgnoreCase("list")) {
            return "list";
        } else if (input.length() >= 6 && input.substring(0, 4).toLowerCase().indexOf("done") != -1) {
            return "mark";
        } else if (input.length() >= 8 && input.substring(0, 6).toLowerCase().indexOf("delete") != -1) {
            return "delete";
        } else if (input.length() >= 6 && input.substring(0, 4).toLowerCase().indexOf("todo") != -1) {
            return "todo";
        } else if (input.length() >= 10 && input.substring(0, 8).toLowerCase().indexOf("deadline") != -1) {
            return "deadline";
        } else if (input.length() >= 7 && input.substring(0, 5).toLowerCase().indexOf("event") != -1) {
            return "event";
        } else if (input.length() >= 6 && input.substring(0, 4).toLowerCase().indexOf("find") != -1) {
            return "find";
        } else if (input.length() >= 3 && input.substring(0, 3).toLowerCase().equals("bye")) {
            return "bye";
        } else {
            return "invalid";
        }
    }
}
