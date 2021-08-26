package duke;

public class Parser {
    Parser() {
    }

    public String parseCommand(String str) throws DukeException {
        if (str.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (str.equalsIgnoreCase("help")) {
            return "help";
        } else if (str.equalsIgnoreCase("list")) {
            return "list";
        } else if (str.equalsIgnoreCase("todo")) {
            return "todo";
        } else if (str.equalsIgnoreCase("deadline")) {
            return "deadline";
        } else if (str.equalsIgnoreCase("event")) {
            return "event";
        } else if (str.equalsIgnoreCase("done")) {
            return "done";
        } else if (str.equalsIgnoreCase("delete")) {
            return "delete";
        } else {
            throw new InvalidInputException();
        }
    }
}
