package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.LostCommand;
import duke.command.TagCommand;
import duke.exception.DukeException;

/**
 * Parser parse user input and create command based on the input.
 */
public class Parser {

    /**
     * Returns command to be execute based on user's command.
     *
     * @param fullCommand User's input text.
     * @return Command to be executed.
     * @throws DukeException if command created has error.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String command = fullCommand.split(" ")[0];
        Command newCommand;

        switch(command) {
        case "exit":
            newCommand = new ExitCommand();
            break;

        case "delete":
            newCommand = Parser.createDeleteCommand(fullCommand);
            break;

        case "list":
            newCommand = new ListCommand();
            break;

        case "done":
            newCommand = Parser.createDoneCommand(fullCommand);
            break;

        case "todo": case "deadline": case "event":
            newCommand = new AddCommand(command, fullCommand);
            break;

        case "find":
            newCommand = Parser.createFindCommand(fullCommand);
            break;

        case "tag":
            newCommand = Parser.createTagCommand(fullCommand);
            break;

        default:
            newCommand = new LostCommand();
            break;
        }

        return newCommand;
    }

    private static Command createDeleteCommand(String fullCommand) throws DukeException {
        Command newCommand;
        try {
            int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]);
            newCommand = new DeleteCommand(taskNumber);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task number.");
        }

        return newCommand;
    }

    private static Command createDoneCommand(String fullCommand) throws DukeException {
        Command newCommand;
        try {
            int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]);
            newCommand = new DoneCommand(taskNumber);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task number.");
        }

        return newCommand;
    }

    private static Command createFindCommand(String fullCommand) throws DukeException {
        Command newCommand;
        try {
            String keyWord = fullCommand.split(" ")[1];
            newCommand = new FindCommand(keyWord);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid keyword.");
        }

        return newCommand;
    }

    private static Command createTagCommand(String fullCommand) throws DukeException {
        Command newCommand;
        try {
            String tag = fullCommand.split("#")[1];
            int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]);
            newCommand = new TagCommand(tag, taskNumber);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid number/tag.\nEg.tag 1 #fun");
        }

        return newCommand;
    }

}
