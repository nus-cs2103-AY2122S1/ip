package duke;

import duke.DukeException.InvalidInputException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String[] words = fullCommand.split(" ", 2);
        String command = words[0], userDescription = words.length > 1 ? words[1] : "";
        String[] taskDescription;
        switch (command) {
        case "list":
            return new ListCommand();
        case "todo":
            return new AddCommand(new Todo(userDescription));
        case "deadline":
            taskDescription = getTaskDescription(userDescription, " /by ");
            return new AddCommand(new Deadline(taskDescription[0], taskDescription[1]));
        case "event":
            taskDescription = getTaskDescription(userDescription, " /at ");
            return new AddCommand(new Event(taskDescription[0], taskDescription[1]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(userDescription));
        default:
            throw new InvalidInputException();
        }
    }

    public static String[] getTaskDescription(String userDescription, String specifier)  {
        return userDescription.split(specifier);
    }
}
