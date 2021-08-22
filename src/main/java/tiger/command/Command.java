package tiger.command;

import tiger.actions.Action;
import tiger.actions.AppState;
import tiger.actions.ByeAction;
import tiger.actions.DeadLineAction;
import tiger.actions.DeleteAction;
import tiger.actions.EventAction;
import tiger.actions.InvalidAction;
import tiger.actions.ListAction;
import tiger.actions.MarkDoneAction;
import tiger.actions.ToDoAction;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.parser.DeadLineParser;
import tiger.parser.DeleteParser;
import tiger.parser.EventParser;
import tiger.parser.MarkDoneParser;
import tiger.parser.Parser;
import tiger.parser.ToDoParser;

/**
 * The {@code Command} class handles deciding which command the user input corresponds to, and is responsible for
 * return an {@code Action} class that can be executed to run that command.
 */

public class Command {

    /**
     * Gets the desired {@code Action} class from the user command.
     *
     * @param command String of user input.
     * @param applicationState The current application state.
     * @return An {@code Action} object.
     * @throws TigerInvalidInputException if the user input is invalid.
     */

    public static Action getActionFromCommand(String command, AppState applicationState) throws TigerInvalidInputException {
        Parser parser = new Parser(command);
        switch (parser.getCommandKeyword()) {
        case "bye":
            return new ByeAction(applicationState);
        case "list":
            return new ListAction(applicationState);
        case "done":
            MarkDoneParser markDoneParser = new MarkDoneParser(command);
            return new MarkDoneAction(applicationState, markDoneParser.index - 1);
        case "delete":
            DeleteParser deleteCommand = new DeleteParser(command);
            return new DeleteAction(applicationState, deleteCommand.index - 1);
        case "todo":
            ToDoParser toDoCommand = new ToDoParser(command);
            return new ToDoAction(applicationState, toDoCommand.todo);
        case "deadline":
            DeadLineParser deadLineCommand = new DeadLineParser(command);
            return new DeadLineAction(applicationState, deadLineCommand.todo, deadLineCommand.dateLine);
        case "event":
            EventParser eventCommand = new EventParser(command);
            return new EventAction(applicationState, eventCommand.todo, eventCommand.eventAt);
        default:
            return new InvalidAction(applicationState);
        }
    }


}
