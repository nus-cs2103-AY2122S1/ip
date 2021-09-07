package duke.parser;

import duke.command.Command;

public class Parser {

    /**
     * Returns a Command after taking in the user's input.
     * What the command does will be completely
     * up to what was entered by the user. Each Command will
     * entail different behaviour from Duke.
     *
     * @param stringCommand This is the input that the user typed into console.
     * @return a Command that can be executed to perform a specific action.
     */
    public static Command parse(final String stringCommand) {
        String keyword = stringCommand.split(" ")[0].trim().toLowerCase();
        assert !keyword.equals("") : "keyword should not be empty";
        switch (keyword) {
        case "list":
            return new Command.ListCommand();
        case "snooze":
            return new Command.SnoozeCommand(stringCommand);
        case "clear":
            return new Command.ClearCommand();
        case "find":
                return new Command.FindCommand(stringCommand);
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
