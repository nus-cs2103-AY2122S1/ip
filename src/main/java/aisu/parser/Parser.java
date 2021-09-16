package aisu.parser;

import java.util.regex.PatternSyntaxException;

import aisu.command.AddCommand;
import aisu.command.Command;
import aisu.command.DeleteCommand;
import aisu.command.ExitCommand;
import aisu.command.FindCommand;
import aisu.command.HelpCommand;
import aisu.command.MarkDoneCommand;
import aisu.command.ShowListCommand;
import aisu.command.TagCommand;
import aisu.exception.AisuException;
import aisu.tasklist.TaskList;

/**
 * A Parser for text inputs.
 *
 * @author Liaw Xin Yan
 */
public class Parser {

    /**
     * Parses the input.
     *
     * @param input String input by user.
     * @return Command to be called.
     * @throws AisuException if input is invalid.
     */
    public static Command parse(String input) throws AisuException {
        assert input.length() >= 1 : "There needs to be text entered by the user.";

        try {
            if (input.equals("bye")) {
                return new ExitCommand();
            } else if (input.equals("help")) {
                return new HelpCommand();
            } else if (input.equals("list")) {
                return new ShowListCommand();
            } else if (input.startsWith("done ")) {
                return new MarkDoneCommand(Integer.parseInt(input.substring(5)));
            } else if (input.startsWith("todo ")) {
                return new AddCommand(TaskList.TaskTypes.T, input.substring(5));
            } else if (input.startsWith("deadline ")) {
                return new AddCommand(TaskList.TaskTypes.D, input.substring(9));
            } else if (input.startsWith("event ")) {
                return new AddCommand(TaskList.TaskTypes.E, input.substring(6));
            } else if (input.startsWith("delete ")) {
                return new DeleteCommand(Integer.parseInt(input.substring(7)));
            } else if (input.startsWith("find ")) {
                return new FindCommand(input.substring(5));
            } else if (input.startsWith("tag ")) {
                String[] parsedData = input.substring(4).split(" /with ");
                return new TagCommand(Integer.parseInt(parsedData[0].strip()), parsedData[1].strip());
            } else {
                throw new AisuException("That's an invalid task format...");
            }
        } catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new AisuException("That's an invalid task format...");
        }
    }
}
