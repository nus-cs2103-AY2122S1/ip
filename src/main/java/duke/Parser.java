package duke;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitDukeCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

public class Parser {

    private static final String BYE_NAME = "bye";
    private static final String LIST_NAME = "list";
    private static final String DONE_NAME = "done";
    private static final String FIND_NAME = "find";
    private static final String DELETE_NAME = "delete";
    private static final String TODO_NAME = "todo";
    private static final String DEADLINE_NAME = "deadline";
    private static final String EVENT_NAME = "event";
    private static final String ERROR_WRONG_INPUT_FORMAT = "OOWOOPS!!!"
            + "I'm sowwie, but I don't know what that mweans :-(";

    /**
     * Parses user input and returns the appropriate Command
     *
     * @param fullCommand user input
     * @return appropriate Command
     */
    public static Command parse(String fullCommand) {
        if (fullCommand.equals(BYE_NAME)) {
            return new ExitDukeCommand(fullCommand);
        } else if (fullCommand.equals(LIST_NAME)) {
            return new ListCommand(fullCommand);
        } else {
            String[] parsedUserInput = fullCommand.split(" ", 2);
            switch (parsedUserInput[0]) {
            case DONE_NAME:
                return new DoneCommand(fullCommand);
            case FIND_NAME:
                return new FindCommand(fullCommand);
            case DELETE_NAME:
                return new DeleteCommand(fullCommand);
            case TODO_NAME:
            case "t":
            case DEADLINE_NAME:
            case "d":
            case EVENT_NAME:
            case "e":
                return new AddTaskCommand(fullCommand);
            default:
                throw new DukeException(ERROR_WRONG_INPUT_FORMAT);
            }
        }
    }
}
