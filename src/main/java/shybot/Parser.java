package shybot;

import java.util.NoSuchElementException;
import java.util.Scanner;

import shybot.command.Command;
import shybot.command.DeadlineCommand;
import shybot.command.DeleteCommand;
import shybot.command.DoneCommand;
import shybot.command.EventCommand;
import shybot.command.ExitCommand;
import shybot.command.FindCommand;
import shybot.command.ListCommand;
import shybot.command.TodoCommand;
import shybot.command.WelcomeCommand;
import shybot.exception.ShyBotParseException;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the full command and returns a Command instance.
     *
     * @param fullCommand The command input by the user.
     * @return A Command instance of the parsed command.
     * @throws ShyBotParseException If parser fails to recognise the format of the command.
     */
    public static Command parse(String fullCommand) throws ShyBotParseException {
        Scanner sc = new Scanner(fullCommand);
        String input = sc.next();
        try {
            switch (input) {
            case "hi": {
                return new WelcomeCommand();
            }
            case "list": {
                return new ListCommand();
            }
            case "done": {
                return new DoneCommand(sc.nextInt());
            }
            case "remove": {
                return new DeleteCommand(sc.nextInt());
            }
            case "todo": {
                return new TodoCommand(sc.nextLine().trim());
            }
            case "deadline": {
                return new DeadlineCommand(sc.nextLine().trim());
            }
            case "event": {
                return new EventCommand(sc.nextLine().trim());
            }
            case "find": {
                return new FindCommand(sc.nextLine().trim());
            }
            case "bye": {
                return new ExitCommand();
            }
            default: {
                throw new ShyBotParseException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            }
        } catch (NoSuchElementException e) {
            throw new ShyBotParseException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } finally {
            sc.close();
        }
    }
}
