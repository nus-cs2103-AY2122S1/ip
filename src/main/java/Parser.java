import java.util.ArrayList;

/**
 * This class handles the making sense of user commands.
 */
public class Parser {

    public Parser() {

    }

    /**
     * Takes in user input and performs an action accordingly.
     */
    public String interpretCommand(String input) throws InvalidCommandException {
        if (input.equalsIgnoreCase("list")) {
            return "list";
        } else if (input.toLowerCase().indexOf("done") != -1) {
            return "mark";
        } else if (input.toLowerCase().indexOf("delete") != -1) {
            return "delete";
        } else if (input.toLowerCase().indexOf("todo") != -1) {
            return "todo";
        } else if (input.toLowerCase().indexOf("deadline") != -1) {
            return "deadline";
        } else if (input.toLowerCase().indexOf("event") != -1) {
            return "event";
        } else if (input.toLowerCase().equals("bye")) {
            return "bye";
        } else {
            return "invalid";
        }
    }
}
