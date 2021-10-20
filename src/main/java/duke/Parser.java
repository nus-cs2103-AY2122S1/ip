package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddRecurringCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

/**
 * Parser that parses input given by the user.
 */
public class Parser {

    private enum Action {
        Done,
        Delete,
        Todo,
        Deadline,
        Event,
        Find,
        Recurring,
    }

    /**
     * Returns Command based on input given by the user.
     *
     * @param str User input
     * @return Command for Duke to run
     * @throws DukeException
     */
    public static Command parse(String str) throws DukeException {

        String[] arr = str.split(" ", 2);
        if (arr.length < 1) {
            throw new DukeException("No command was given.");
        }
        String firstWord = arr[0];
        if (str.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (str.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else {
            Action action = parseFirstWord(firstWord.toLowerCase());

            if (arr.length < 2 && (action == Action.Done || action == Action.Delete)) {
                throw new DukeException("duke.task.Task number for " + firstWord + " is not given.");
            } else if (arr.length < 2 || arr[1].isBlank()) {
                throw new DukeException("The description of " + firstWord + " cannot be empty");
            } else {
                return actionToCommand(action, arr[1]);
            }
        }
    }

    private static Action parseFirstWord(String firstWord) throws DukeException {
        switch (firstWord) {
        case "done":
            return Action.Done;
        case "delete":
            return Action.Delete;
        case "todo":
            return Action.Todo;
        case "deadline":
            return Action.Deadline;
        case "event":
            return Action.Event;
        case "find":
            return Action.Find;
        case "recurring":
            return Action.Recurring;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }


    private static Command actionToCommand(Action action, String str) throws DukeException {
        switch (action) {
        case Done:
            return new DoneCommand(str);
        case Delete:
            return new DeleteCommand(str);
        case Todo:
            return new AddToDoCommand(str);
        case Deadline:
            return new AddDeadlineCommand(str);
        case Event:
            return new AddEventCommand(str);
        case Find:
            return new FindCommand(str);
        case Recurring:
            return new AddRecurringCommand(str);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }


}
