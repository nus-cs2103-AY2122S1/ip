package bobbybot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bobbybot.commands.AddCommand;
import bobbybot.commands.AddContactCommand;
import bobbybot.commands.Command;
import bobbybot.commands.DeleteCommand;
import bobbybot.commands.DeleteContactCommand;
import bobbybot.commands.DoneCommand;
import bobbybot.commands.ExitCommand;
import bobbybot.commands.FindCommand;
import bobbybot.commands.IncorrectCommand;
import bobbybot.commands.ListCommand;
import bobbybot.commands.ListContactsCommand;
import bobbybot.enums.BotCommand;
import bobbybot.exceptions.InvalidArgumentException;
import bobbybot.person.Address;
import bobbybot.person.Email;
import bobbybot.person.Name;
import bobbybot.person.Phone;

/**
 * Class helps to handle parsing of user commands
 */
public class Parser {

    public static final Pattern DATA_ARGS_FORMAT =
            Pattern.compile("(?<type>[^/]+)(?:/by|/at)(?<time>[^/]+)");

    public static final Pattern NUMBER_ARGS_FORMAT =
            Pattern.compile("\\d+");

    //@@author AB3-reuse
    public static final Pattern CONTACT_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<name>[^/]+)"
                    + "p/(?<phone>[^/]+)"
                    + "e/(?<email>[^/]+)"
                    + "a/(?<address>[^/]+)");
    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input and creates commands
     * @param userInput userInput to bot
     */
    public Command parseCommand(String userInput) {
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
                return prepareAdd(arguments, BotCommand.TODO);
            case EVENT:
                return prepareAdd(arguments, BotCommand.EVENT);
            case DEADLINE:
                return prepareAdd(arguments, BotCommand.DEADLINE);
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
            case CONTACT:
                return prepareAddContact(arguments);
            case LIST_CONTACT:
                return new ListContactsCommand();
            case DELETE_CONTACT:
                return prepareDeleteContact(arguments);
            default:
                System.out.println("Invalid command");
            }
            return new IncorrectCommand();
        } catch (InvalidArgumentException | IllegalArgumentException e) {
            return new IncorrectCommand();
        }
    }

    private Command prepareAdd(String args, BotCommand type) throws InvalidArgumentException {
        // Check arg string format to prevent errors
        final Matcher matcher = DATA_ARGS_FORMAT.matcher(args.trim());
        if (type.equals(BotCommand.TODO)) {
            return new AddCommand(args.trim());
        }
        if (!matcher.matches()) {
            return new IncorrectCommand();
        }
        // Add event or deadline
        if (args.contains("/by") & type.equals(BotCommand.DEADLINE)) {
            return new AddCommand(matcher.group("type").trim(), matcher.group("time").trim(), type);
        } else if (args.contains("/at") & type.equals(BotCommand.EVENT)) {
            return new AddCommand(matcher.group("type").trim(), matcher.group("time").trim(), type);
        } else {
            System.out.println("Failed to add event/deadline");
            return new IncorrectCommand();
        }
    }

    private Command prepareAddContact(String args) throws InvalidArgumentException {
        // Check arg string format to prevent errors
        final Matcher matcher = CONTACT_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand();
        }

        Name name = new Name(matcher.group("name").trim());
        Email email = new Email(matcher.group("email").trim());
        Phone phone = new Phone(matcher.group("phone").trim());
        Address address = new Address("address");

        System.out.println(name);
        System.out.println(email);
        System.out.println(phone);
        System.out.println(address);
        return new AddContactCommand(name, email, phone, address);
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

    private Command prepareDeleteContact(String args) {
        final Matcher matcher = NUMBER_ARGS_FORMAT.matcher(args.trim());
        // Check if arg is parsable as integer
        if (!matcher.matches()) {
            return new IncorrectCommand();
        }
        return new DeleteContactCommand(Integer.parseInt(args.trim()));
    }
}
