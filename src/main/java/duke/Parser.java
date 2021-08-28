package duke;
public class Parser {

    public static Command parse(String fullCommand) {
        String[] words = fullCommand.split(" ", 2);
        String command = words[0];
        switch (command) {
        case "todo":
            return new AddCommand(new Todo(words[1]));
        case "deadline":
            return new AddCommand(new Deadline("test deadline", "test by-date"));
        case "event":
            return new AddCommand(new Event("test event", "test at-date"));
        default:
            return new AddCommand(new Todo("default test"));
        }
    }
}
