public class Parser {
    static void parseTask(String input) throws DukeException {
        switch (input) {
            case "deadline" -> throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            case "event" -> throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            case "todo" -> throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
}