package duke;

import duke.command.Command;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.IncorrectCommand;


public class Parser {
    /**
     * Parse a string to a Command
     *
     * @param fullCommand the command in string
     * @return            the command in Command class
     */
    public static Command parse(String fullCommand) {
        String[] inputValues = fullCommand.split(" ", 2);
        String commandInstruction = inputValues[0];

        String parameter_1 = getParameters(inputValues)[0];
        String parameter_2 = getParameters(inputValues)[1];

        try {
            switch (commandInstruction) {
                case ListCommand.INSTRUCTION:
                    return new ListCommand();
                case TodoCommand.INSTRUCTION:
                    return new TodoCommand(parameter_1);
                case DeadlineCommand.INSTRUCTION:
                    return new DeadlineCommand(parameter_1, parameter_2);
                case EventCommand.INSTRUCTION:
                    return new EventCommand(parameter_1, parameter_2);
                case DoneCommand.INSTRUCTION:
                    return new DoneCommand(parameter_1);
                case DeleteCommand.INSTRUCTION:
                    return new DeleteCommand(parameter_1);
                case ExitCommand.INSTRUCTION:
                    return new ExitCommand();
                default:
                    return new IncorrectCommand("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        catch (DukeException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Retrieve the parameters from an array of String input values
     *
     * @param inputValues an array of input values
     * @return            an array of parameters
     */
    public static String[] getParameters(String[] inputValues) {
        String parameter_1 = "";
        String parameter_2 = "";

        if (inputValues.length > 1) {
            String[] inputParameters = inputValues[1].split("\\s/((at)|(by))\\s");
            if (inputParameters.length > 1) {
                parameter_1 = inputParameters[0];
                parameter_2 = inputParameters[1];
            } else {
                parameter_1 = inputParameters[0];
            }
        }

        return new String[]{parameter_1, parameter_2};
    }
}
