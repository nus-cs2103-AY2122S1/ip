package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.DukeException.InvalidInputException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String[] words = fullCommand.split(" ", 2);
        String command = words[0], userDescription = words.length > 1 ? words[1] : null;
        String[] taskDescriptions;

        switch (command) {
        case "list":
            return new ListCommand();
        case "todo":
            return new AddCommand(new Todo(userDescription));
        case "deadline":
            taskDescriptions = getTaskDescription(userDescription, " /by ");
            return new AddCommand(new Deadline(taskDescriptions[0], taskDescriptions[1]));
        case "event":
            taskDescriptions = getTaskDescription(userDescription, " /at ");
            return new AddCommand(new Event(taskDescriptions[0], taskDescriptions[1]));
        case "done":
            return new DoneCommand(Integer.parseInt(userDescription));
        case "delete":
            return new DeleteCommand(Integer.parseInt(userDescription));
        case "find":
            return new FindCommand(userDescription);
        case "exit":
            return new ExitCommand();
        default:
            throw new InvalidInputException();
        }
    }

    public static String[] getTaskDescription(String userDescription, String specifier)  {
        return userDescription.split(specifier);
    }
}
