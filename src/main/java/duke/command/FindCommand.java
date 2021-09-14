package duke.command;

import duke.commandresult.CommandResult;
import duke.tasklist.TaskList;

/**
 * A command that can signify Duke to find a task.
 */
public class FindCommand extends Command {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "find";

    public static final String DESCRIPTION = "Find a task using a search string";

    public static final String FORMAT = COMMAND_WORD + " the_search_string";

    /** The word to search by the user passed on by Duke*/
    private final String searchInput;

    /**
     * Constructor that creates FindCommand.
     * @param taskList The TaskList to be given by Duke.
     * @param searchInput The input string to be searched by Duke.
     */
    public FindCommand(TaskList taskList, String searchInput) {
        super(taskList);
        this.searchInput = searchInput;
    }

    public static String formatAndDescription() {
        return COMMAND_WORD + ": " + DESCRIPTION + "\n" + FORMAT;
    }

    /**
     * Overrides execute() from Command and returns a CommandResult which stores the feedback string
     * to be returned to the UserInterface.
     * @return CommandResult to be rendered by UserInterface.
     */
    @Override
    public CommandResult execute() {
        TaskList taskList = super.getTaskList();
        TaskList filtered = taskList.filter(this.searchInput);
        String feedback = "Here are the matching tasks in your list:\n" + filtered.toString();
        return new CommandResult(feedback, false);
    }
}
