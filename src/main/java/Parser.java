import java.util.List;
import java.util.Locale;

public class Parser {

    public static Command parse(String command) {
        String first = command.toLowerCase().split(" ")[0];
        switch (first) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            break;
        case "deadline":
            break;
        case "event":
            break;
        case "delete":
            break;
        case "done":
            break;
        case "save":
            return new SaveCommand();
        default:
            return new InvalidCommand();
        }

        // Placeholder to remove error
        return new InvalidCommand();
    }
}
