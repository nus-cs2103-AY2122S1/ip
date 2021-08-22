package duke;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ListCommand;

public class Parser {
    public static Command parse(String line) {
        Command c;
        String[] lineSegments = line.split(" ");
        switch (lineSegments[0].toLowerCase()) {
        case "todo":
            c = new AddTodoCommand();
            break;
        case "event":
            c = new AddEventCommand();
            break;
        case "deadline":
            c = new AddDeadlineCommand();
            break;
        case "list":
            c = new ListCommand();
            break;
        case "done":
            c = new DoneCommand();
            break;
        case "delete":
            c = new DeleteCommand();
            break;
        case "bye":
            c = new ByeCommand();
            break;
        default:
            throw new DukeException("Command not understood");
        }
        c.parseLine(line);
        return c;
    }
}
