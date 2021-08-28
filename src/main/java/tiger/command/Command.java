package tiger.command;

import tiger.actions.Action;
import tiger.actions.ByeAction;
import tiger.actions.ClearAction;
import tiger.actions.DeadLineAction;
import tiger.actions.DeleteAction;
import tiger.actions.EventAction;
import tiger.actions.FindAction;
import tiger.actions.InvalidAction;
import tiger.actions.ListAction;
import tiger.actions.MarkDoneAction;
import tiger.actions.StorageLoadAction;
import tiger.actions.ToDoAction;
import tiger.app.AppState;
import tiger.constants.Flag;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.exceptions.inputs.TigerSemiColonException;
import tiger.parser.DeadLineParser;
import tiger.parser.DeleteParser;
import tiger.parser.EventParser;
import tiger.parser.FindParser;
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

    public static Action getActionFromCommand(String command, AppState applicationState) throws
            TigerInvalidInputException {
        if (applicationState.checkFlag().equals(Flag.STORAGE_FAILED)) {
            AppState newApplicationState;
            if (!Parser.isValid(command)) {
                throw new TigerSemiColonException("");
            }
            switch (Parser.getCommandKeyword(command)) {
            case "y":
                newApplicationState = new AppState(applicationState.getTaskList(),
                        Flag.STORAGE_PARTIAL_LOAD);
                return new StorageLoadAction(newApplicationState);
            case "n":
                newApplicationState = new AppState(applicationState.getTaskList(),
                        Flag.STORAGE_WIPE);
                return new StorageLoadAction(newApplicationState);
            default:
                newApplicationState = new AppState(applicationState.getTaskList(),
                        Flag.STORAGE_FAILED);
                return new StorageLoadAction(newApplicationState);
            }
        }
        switch (Parser.getCommandKeyword(command)) {
        case "exit":
            // Fallthrough
        case "quit":
            // Fallthrough
        case "bye":
            return new ByeAction(applicationState);
        case "ls":
            // Fallthrough
        case "list":
            return new ListAction(applicationState);
        case "done":
            MarkDoneParser markDoneParser = new MarkDoneParser(command);
            markDoneParser.parse();
            return new MarkDoneAction(applicationState, markDoneParser.getIndex() - 1);
        case "remove":
            // Fallthrough
        case "del":
            // Fallthrough
        case "delete":
            DeleteParser deleteCommand = new DeleteParser(command);
            deleteCommand.parse();
            return new DeleteAction(applicationState, deleteCommand.getIndex() - 1);
        case "todo":
            ToDoParser toDoCommand = new ToDoParser(command);
            toDoCommand.parse();
            return new ToDoAction(applicationState, toDoCommand.getTodo(), toDoCommand.getPriority());
        case "dateline":
            // Fallthrough;
        case "deadline":
            DeadLineParser deadLineCommand = new DeadLineParser(command);
            deadLineCommand.parse();
            return new DeadLineAction(applicationState, deadLineCommand.getTodo(), deadLineCommand.getDate(),
                    deadLineCommand.getPriority());
        case "event":
            EventParser eventCommand = new EventParser(command);
            eventCommand.parse();
            return new EventAction(applicationState, eventCommand.getTodo(), eventCommand.getDate(),
                    eventCommand.getPriority());
        case "search":
            // Fallthrough
        case "find":
            FindParser findCommand = new FindParser(command);
            findCommand.parse();
            return new FindAction(applicationState, findCommand.getToSearchFor());
        case "clear":
            return new ClearAction(applicationState);
        default:
            return new InvalidAction(applicationState);
        }
    }


}
