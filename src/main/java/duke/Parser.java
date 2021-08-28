package duke;
public class Parser {

    public static Command parse(String fullCommand) {
        String[] words = fullCommand.split(" ", 2);
        String command = words[0], userDescription = words.length > 1 ? words[1] : "";
        String[] taskDescription;
        switch (command) {
        case "list":
            return new ListCommand();
        case "todo":
            return new AddCommand(new Todo(words[1]));
        case "deadline":
            taskDescription = getTaskDescription(userDescription, " /by ");
            return new AddCommand(new Deadline(taskDescription[0], taskDescription[1]));
        case "event":
            taskDescription = getTaskDescription(userDescription, " /at ");
            return new AddCommand(new Event(taskDescription[0], taskDescription[1]));
        default:
            return new AddCommand(new Todo("default test"));
        }
    }

    public static String[] getTaskDescription(String userDescription, String specifier)  {
        return userDescription.split(specifier);
    }
}
