package bobbybot.util;

import bobbybot.commands.AddCommand;
import bobbybot.commands.Command;
import bobbybot.commands.DoneCommand;
import bobbybot.commands.ExitCommand;
import bobbybot.commands.FindCommand;
import bobbybot.commands.ListCommand;
import bobbybot.enums.BotCommand;
import bobbybot.exceptions.InvalidArgumentException;
import bobbybot.exceptions.TooManyArgumentsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class helps to handle parsing of user commands
 */
public class Parser {

    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    public static final Pattern DATA_ARGS_FORMAT =
            Pattern.compile("(?<type>[^/]+)(?:/by|/at)(?<time>[^/]+)");

    public static final Pattern NUMBER_ARGS_FORMAT =
            Pattern.compile("\\d+");
    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private final TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * Initialises Parser object with TaskList and Ui
     * @param tasks taskList of tasks currently loaded
     * @param ui Ui
     */
    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }


    /**
     * Parses user input and creates commands
     * @param userInput
     */
    public Command parseCommand(String userInput) throws InvalidArgumentException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            //return new IncorrectCommand();
        }

        final String command = matcher.group("commandWord").toUpperCase();
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
        default:
            System.out.println("Invalid command");
            return null;
        }
    }

    private Command prepareAdd(String args) throws InvalidArgumentException {
        final Matcher matcher = DATA_ARGS_FORMAT.matcher(args.trim());
        // Check arg string format
        if (!matcher.matches()) { // Add todo
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
            //return new IncorrectCommand();
        }
        return new DoneCommand(Integer.parseInt(args.trim()));
    }

    /**
     * Performs command based on String user input
     * @param userInput string command for chatbot
     * @throws InvalidArgumentException Invalid or no arguments given
     * @throws TooManyArgumentsException Too many /by or /at connectors
     */
    /*
    public void parseCommand(String userInput, TaskList tasks, Ui ui) throws InvalidArgumentException,
            TooManyArgumentsException {
        List<String> userInputList = new LinkedList<>(Arrays.asList(userInput.split(" ")));
        BotCommand command = BotCommand.valueOf(userInputList.get(0).toUpperCase());
        String description;
        String[] userInputArgs;
        switch (command) {
        case BYE:
            ui.sayBye();
            break;
        case LIST:
            tasks.printList();
            break;
        case DONE:
            tasks.markAsDone(Integer.parseInt(userInputList.get(1)));
            break;
        case DELETE:
            // check delete argument
            if (!isNumeric(userInputList.get(1))) {
                throw new InvalidArgumentException("Delete argument is not numeric");
            }
            tasks.deleteTask(Integer.parseInt(userInputList.get(1)));
            break;
        case TODO:
            userInputList.remove(0);
            if (userInputList.size() == 0) {
                throw new InvalidArgumentException("No arguments submitted for todo");
            }
            description = String.join(" ", userInputList);
            tasks.createToDo(description);
            break;
        case DEADLINE:
            userInputList.remove(0);
            if (userInputList.size() == 0) {
                throw new InvalidArgumentException("No arguments submitted for deadline");
            }
            //split description and by
            userInputArgs = String.join(" ", userInputList).split("/by ");
            if (userInputArgs.length > 2) {
                throw new TooManyArgumentsException("Too many arguments given for deadline");
            } else if (userInputArgs.length == 1) {
                throw new InvalidArgumentException("Could not find connector /by ");
            }
            description = userInputArgs[0];
            String by = userInputArgs[1];
            tasks.createDeadline(description, by);
            break;
        case EVENT:
            userInputList.remove(0);
            if (userInputList.size() == 0) {
                throw new InvalidArgumentException("No arguments submitted for event");
            }
            //split description and at
            userInputArgs = String.join(" ", userInputList).split("/at ");
            if (userInputArgs.length > 2) {
                throw new TooManyArgumentsException("Too many arguments given");
            } else if (userInputArgs.length == 1) {
                throw new InvalidArgumentException("Could not find connector /at");
            }
            description = userInputArgs[0];
            String at = userInputArgs[1];
            tasks.createEvent(description, at);
            break;
        case FIND:
            String keyword = userInputList.get(1);
            tasks.findMatchingTasks(keyword);
            break;
        default:
            System.out.println("Invalid keyword");
        }
    }
    */

}
