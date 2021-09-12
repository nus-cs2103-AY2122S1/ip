package bot.utility;

import bot.commands.AddCommand;
import bot.commands.Command;
import bot.commands.DeleteCommand;
import bot.commands.DoneCommand;
import bot.commands.EndCommand;
import bot.commands.ErrorCommand;
import bot.commands.FindCommand;
import bot.commands.ListCommand;
import bot.error.DukeException;

/**
 * Represents a parser that can read and understand what to do with an input String.
 */
public class Parser {

    /**
     * Returns a Command containing information according to the instruction by the user.
     */
    public Command parse(String input) {
        String[] words = input.trim().split(" ", 2);
        try {
            checkInput(words);
        } catch (DukeException e) {
            return new ErrorCommand(e.getMessage());
        }
        switch (words[0]) {
        case "bye":
            return new EndCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(words[1]);
        case "delete":
            return new DeleteCommand(words[1]);
        case "find":
            return new FindCommand(words[1]);
        case "todo":
            return new AddCommand("todo", words[1]);
        case "deadline":
            return new AddCommand("deadline", words[1]);
        case "event":
            return new AddCommand("event", words[1]);
        default:
            return new ErrorCommand();
        }
    }

    /**
     * Handles DukeExceptions for the chatBot
     * @param log The separate words in a given line
     */
    private static void checkInput(String[] log) throws DukeException {
        if (log.length == 1) {
            switch (log[0]) {
            case "done":
                throw new DukeException("\nWhat do you wish to mark as done?");
            case "todo":
                throw new DukeException("\nOh dear!. The description of a todo cannot be empty!");
            case "deadline":
                throw new DukeException("\nOh lord!. I need some description and a time limit!");
            case "event":
                throw new DukeException("\nBy the heavens!. I need some description and a timing!");
            case "find":
                throw new DukeException("\nI need something to search for!");
            case "delete":
                throw new DukeException("\nI need something to delete!");
            default:
                break;
            }
        } else if (log.length == 2) {
            switch (log[0]) {
            case "deadline":
                if (log[1].split(" /by ", 2).length != 2) {
                    throw new DukeException("\nBlimey! Did you forget to type \"/by\" or a time limit?");
                }
                break;
            case "event":
                if (log[1].split(" /at ", 2).length != 2) {
                    throw new DukeException("\nWait! Did you forget to type \"/at\" or a timing?");
                }
                break;
            case "done":
            case "delete":
                if (TaskList.isShorterOrEqualTo(Integer.parseInt(log[1]))) {
                    throw new DukeException("\nI don't have that many tasks!");
                }
                break;
            default:
                break;
            }
        }
    }

    protected boolean canUnderstand(String input) {
        return input.startsWith("bye") || input.startsWith("list") || input.startsWith("done")
                || input.startsWith("delete") || input.startsWith("find") || input.startsWith("todo")
                || input.startsWith("deadline") || input.startsWith("event");
    }
}
