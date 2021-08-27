public class Parser {

    public static Command parse(String stringCommand) {
        String keyword = stringCommand.split(" ")[0].trim().toLowerCase();
        switch (keyword) {
            case "list":
                return new Command.ListCommand();
            case "done":
                return new Command.DoneCommand(stringCommand);
            case "todo":
                return new Command.TodoCommand(stringCommand);
            case "event":
                return new Command.EventCommand(stringCommand);
            case "deadline":
                return new Command.DeadlineCommand(stringCommand);
            case "delete":
                return new Command.DeleteCommand(stringCommand);
            case "bye":
                return new Command.ExitCommand();
            default:
                return new Command.NonsenseCommand(stringCommand);
        }

    }
}
