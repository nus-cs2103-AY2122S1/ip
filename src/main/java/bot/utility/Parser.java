package bot.utility;

import bot.commands.AddCommand;
import bot.commands.Command;
import bot.commands.DeleteCommand;
import bot.commands.DoneCommand;
import bot.commands.EndCommand;
import bot.commands.ErrorCommand;
import bot.commands.FindCommand;
import bot.commands.HelpCommand;
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
        case "help":
            return new HelpCommand();
        default:
            return new ErrorCommand();
        }
    }

    /**
     * Handles DukeExceptions for the chatBot
     * @param input The separate words in a given line
     */
    private static void checkInput(String[] input) throws DukeException {
        switch (input.length) {
        case 1:
            checkOneInput(input);
            break;
        case 2:
            checkTwoInputs(input);
            break;
        default:
            break;
        }
    }

    private static void checkOneInput(String[] input) throws DukeException {
        switch (input[0]) {
        case "done":
            throw new DukeException("What do you wish to mark as done?");
        case "todo":
            throw new DukeException("Oh dear!. The description of a todo cannot be empty!");
        case "deadline":
            throw new DukeException("Oh lord!. I need some description and a time limit!");
        case "event":
            throw new DukeException("By the heavens!. I need some description and a timing!");
        case "find":
            throw new DukeException("I need something to search for!");
        case "delete":
            throw new DukeException("I need something to delete!");
        default:
            break;
        }
    }

    private static void checkTwoInputs(String[] input) throws DukeException {
        switch (input[0]) {
        case "deadline":
            if (input[1].split(" /by ", 2).length != 2) {
                throw new DukeException("Blimey! Did you forget to type \"/by\" or a time limit?");
            }
            break;
        case "event":
            if (input[1].split(" /at ", 2).length != 2) {
                throw new DukeException("Wait! Did you forget to type \"/at\" or a timing?");
            }
            break;
        case "done":
        case "delete":
            if (TaskList.isShorterThan(Integer.parseInt(input[1]))) {
                throw new DukeException("I don't have that many tasks!");
            }
            break;
        case "help":
            throw new DukeException("Hmm, did you mean \"help\"?");
        default:
            break;
        }
    }

    protected boolean canUnderstand(String input) {
        return input.startsWith("bye") || input.startsWith("list") || input.startsWith("done")
                || input.startsWith("delete") || input.startsWith("find") || input.startsWith("todo")
                || input.startsWith("deadline") || input.startsWith("event") || input.startsWith("help");
    }
}
