package duke;

import duke.commands.Command;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.DeleteCommand;
import duke.commands.TodoCommand;
import duke.commands.EventCommand;
import duke.commands.DeadlineCommand;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    public Command parse(String fullCommand) throws DukeException {
        Matcher matcher = COMMAND_FORMAT.matcher(fullCommand.trim());
        if (!matcher.matches()) {
            throw new DukeException("Invalid Command Entered");
        }
        String command = matcher.group("command");
        String arguments = matcher.group("arguments");
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "todo":
            return new TodoCommand(arguments);
        case "event":
            return new EventCommand(arguments);
        case "deadline":
            return new DeadlineCommand(arguments);
        default:
            throw new DukeException("Invalid Command Entered");
        }
    }
}
