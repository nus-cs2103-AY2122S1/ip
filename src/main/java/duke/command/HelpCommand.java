package duke.command;

import duke.CommandParser;
import duke.DukeException;

/**
 * Represents a command to see available commands
 */
public class HelpCommand implements Command {
    private static final String GENERAL_HELP = String.format(
            "Available commands: %s, %s, %s, %s, %s, %s, %s, %s\n"
            + "Type `help [command]` to see more about [command]",
            CommandParser.DEADLINE_PREFIX,
            CommandParser.DELETE_PREFIX,
            CommandParser.DONE_PREFIX,
            CommandParser.EVENT_PREFIX,
            CommandParser.EXIT_PREFIX,
            CommandParser.FIND_PREFIX,
            CommandParser.LIST_PREFIX,
            CommandParser.TODO_PREFIX);

    private static final String DEADLINE_HELP =
            "deadline [description] /by [yyyy-MM-dd] [HHmm], e.g. deadline homework /by 2021-09-04 2359\n"
            + "- Add a deadline [description] to be done before [yyyy-MM-dd] [HHmm]\n";

    private static final String DELETE_HELP =
            "delete [index], e.g. delete 2\n - Delete a task at index [index]";

    private static final String DONE_HELP =
            "done [index], e.g. done 1\n"
            + "- Mark a task at index [index] as done";

    private static final String EVENT_HELP =
            "event [description] /at [yyyy-MM-dd] [HHmm], e.g. event meeting /at 2021-09-10 1000\n"
            + "- Add a event [description] that will happen on [yyyy-MM-dd] [HHmm]";

    private static final String EXIT_HELP =
            "exit\n"
            + "- Exit the conversation";

    private static final String FIND_HELP =
            "find [keyword], e.g. find work\n"
            + "- Find tasks with keyword [keyword]";

    private static final String LIST_HELP =
            "list\n"
            + "- List all the tasks";

    private static final String TODO_HELP =
            "todo [description], e.g. todo eat lunch\n"
            + "- Add a todo task [description]";

    private String commandPrefix;

    public HelpCommand(String commandPrefix) {
        this.commandPrefix = commandPrefix;
    }

    @Override
    public String run() throws DukeException {
        switch (this.commandPrefix) {
        case CommandParser.DEADLINE_PREFIX:
            return DEADLINE_HELP;
        case CommandParser.DELETE_PREFIX:
            return DELETE_HELP;
        case CommandParser.DONE_PREFIX:
            return DONE_HELP;
        case CommandParser.EVENT_PREFIX:
            return EVENT_HELP;
        case CommandParser.EXIT_PREFIX:
            return EXIT_HELP;
        case CommandParser.FIND_PREFIX:
            return FIND_HELP;
        case CommandParser.LIST_PREFIX:
            return LIST_HELP;
        case CommandParser.TODO_PREFIX:
            return TODO_HELP;
        default:
            return GENERAL_HELP;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
