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
     * Returns a Command containing information according to the isntruction by the user.
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
            case "todo":
                throw new DukeException("\n\t Oh dear!. The description of a todo cannot be empty!");
            case "deadline":
                throw new DukeException("\n\t Oh lord!. I need some description and a time limit!");
            case "event":
                throw new DukeException("\n\t By the heavens!. I need some description and a timing!");
            case "find":
                throw new DukeException("\n\t I need something to search for!");
            default:
                break;
            }
        } else if (log.length == 2) {
            switch (log[0]) {
            case "deadline":
                if (log[1].split(" /by ", 2).length != 2) {
                    throw new DukeException("\n\t Blimey! Did you forget to type \"/by\" or a time limit?");
                }
                break;
            case "event":
                if (log[1].split(" /at ", 2).length != 2) {
                    throw new DukeException("\n\t Wait! Did you forget to type \"/at\" or a timing?");
                }
                break;
            default:
                break;
            }
        }
    }
}
