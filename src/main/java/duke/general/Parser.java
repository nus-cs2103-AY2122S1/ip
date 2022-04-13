package duke.general;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ErrorCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.RevertCommand;


/**
 * Deals with making sense of the user command
 */
public class Parser {
    /**
     * Takes in a String as input and parses it give back a Command
     * @param fullCommand String input by the user
     * @return Command created based on input by user
     */
    public Command parse(String fullCommand) {
        assert(fullCommand == null) : "Null command cannot be parsed!";

        String[] inputSplit = fullCommand.split(" ", 2);
        Command c = null;
        try {
            switch(inputSplit[0]) {
            case "todo":
                c = new AddCommand(TaskType.TODO, inputSplit);
                break;
            case "deadline":
                c = new AddCommand(TaskType.DEADLINE, inputSplit);
                break;
            case "event":
                c = new AddCommand(TaskType.EVENT, inputSplit);
                break;
            case "list":
                c = new ListCommand();
                break;
            case "done":
                c = new DoneCommand(inputSplit);
                break;
            case "delete":
                c = new DeleteCommand(inputSplit);
                break;
            case "bye":
                c = new ByeCommand();
                break;
            case "find":
                c = new FindCommand(inputSplit);
                break;
            case "undo":
                c = new RevertCommand();
                break;
            default:
                c = new ErrorCommand();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}
