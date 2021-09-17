package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.IncorrectCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.UpdateCommand;

/**
 * Class to encapsulate a Parser
 */
public class Parser {
    /**
     * Parses a string to a Command
     *
     * @param fullCommand the command in string
     * @return            the command in Command class
     */
    public static Command parse(String fullCommand) {
        String[] inputValues = fullCommand.split(" ", 2);
        String commandInstruction = inputValues[0];

        String parameter1 = getParameters(inputValues)[0];
        String parameter2 = getParameters(inputValues)[1];
        String parameter3 = getParameters(inputValues)[2];

        try {
            switch (commandInstruction) {
            case ListCommand.INSTRUCTION_LIST:
                return new ListCommand();
            case TodoCommand.INSTRUCTION_TODO:
                return new TodoCommand(parameter1);
            case DeadlineCommand.INSTRUCTION_DEADLINE:
                return new DeadlineCommand(parameter1, parameter2);
            case EventCommand.INSTRUCTION_EVENT:
                return new EventCommand(parameter1, parameter2);
            case DoneCommand.INSTRUCTION_DONE:
                return new DoneCommand(parameter1);
            case DeleteCommand.INSTRUCTION_DELETE:
                return new DeleteCommand(parameter1);
            case FindCommand.INSTRUCTION_FIND:
                return new FindCommand(parameter1);
            case UpdateCommand.INSTRUCTION_UPDATE:
                return new UpdateCommand(parameter1, parameter2, parameter3);
            case ExitCommand.INSTRUCTION_EXIT:
                return new ExitCommand();
            default:
                return new IncorrectCommand("OOPS!!! I'm sorry, but I don't know what "
                        + "that means :-(");
            }
        } catch (DukeException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Retrieves the parameters from an array of String input values
     *
     * @param inputValues an array of input values
     * @return            an array of parameters
     */
    public static String[] getParameters(String[] inputValues) {
        String parameter1 = "";
        String parameter2 = "";
        String parameter3 = "";

        if (inputValues.length > 1) {
            if (inputValues[1].contains("-")) {
                String[] inputParameters = inputValues[1].split(" ", 3);
                if (inputParameters.length > 2) {
                    parameter1 = inputParameters[0];
                    parameter2 = inputParameters[1];
                    parameter3 = inputParameters[2];
                } else {
                    parameter1 = inputParameters[0];
                    parameter2 = inputParameters[1];
                }
            } else {
                String[] inputParameters = inputValues[1].split("\\s/((at)|(by))\\s");
                if (inputParameters.length > 1) {
                    parameter1 = inputParameters[0];
                    parameter2 = inputParameters[1];
                } else {
                    parameter1 = inputParameters[0];
                }
            }
        }

        return new String[]{parameter1, parameter2, parameter3};
    }
}
