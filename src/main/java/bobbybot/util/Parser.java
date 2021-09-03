package bobbybot.util;

import bobbybot.commands.AddCommand;
import bobbybot.commands.Command;
import bobbybot.commands.DeleteCommand;
import bobbybot.commands.DoneCommand;
import bobbybot.commands.ExitCommand;
import bobbybot.commands.FindCommand;
import bobbybot.commands.IncorrectCommand;
import bobbybot.commands.ListCommand;
import bobbybot.enums.BotCommand;
import bobbybot.exceptions.InvalidArgumentException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class helps to handle parsing of user commands
 */
public class Parser {

    public static final Pattern DATA_ARGS_FORMAT =
            Pattern.compile("(?<type>[^/]+)(?:/by|/at)(?<time>[^/]+)");

    public static final Pattern NUMBER_ARGS_FORMAT =
            Pattern.compile("\\d+");
    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input and creates commands
     * @param userInput userInput to bot
     */
    public Command parseCommand(String userInput) throws InvalidArgumentException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand();
        }

        final String command = matcher.group("commandWord").toUpperCase();
        try {
            BotCommand enumCommand = BotCommand.valueOf(command);
            final String arguments = matcher.group("arguments");

            switch (enumCommand) {
            case TODO:
                //Fallthrough
            case EVENT:
                //Fallthrough
            case DEADLINE:
                return prepareAdd(arguments);
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            case DONE:
                return prepareDone(arguments);
            case FIND:
                return new FindCommand(arguments);
            case DELETE:
                return prepareDelete(arguments);
            default:
                System.out.println("Invalid command");
                return null;
            }
        } catch (IllegalArgumentException e) {
            return new IncorrectCommand();
        }
    }

    private Command prepareAdd(String args) throws InvalidArgumentException {
        // Check arg string format
        final Matcher matcher = DATA_ARGS_FORMAT.matcher(args.trim());
        // Add todo
        if (!matcher.matches()) {
            return new AddCommand(args.trim());
        }
        // Add event or deadline
        if (args.contains("/by")) {
            return new AddCommand(matcher.group("type").trim(), matcher.group("time").trim(), "deadline");
        } else if (args.contains("/at")) {
            return new AddCommand(matcher.group("type").trim(), matcher.group("time").trim(), "event");
        } else {
            System.out.println("Failed to add event/deadline");
            return null;
        }
    }

    private Command prepareDone(String args) {
        final Matcher matcher = NUMBER_ARGS_FORMAT.matcher(args.trim());
        // Check if arg is parsable as integer
        if (!matcher.matches()) {
            return new IncorrectCommand();
        }
        return new DoneCommand(Integer.parseInt(args.trim()));
    }

    private Command prepareDelete(String args) {
        final Matcher matcher = NUMBER_ARGS_FORMAT.matcher(args.trim());
        // Check if arg is parsable as integer
        if (!matcher.matches()) {
            return new IncorrectCommand();
        }
        return new DeleteCommand(Integer.parseInt(args.trim()));
    }
}
