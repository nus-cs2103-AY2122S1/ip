package bot.commands;

import static bot.constants.GlobalStringFormats.LINE_BREAK;

/**
 * Represents a command to give helpful instructions to the user.
 */
public class HelpCommand extends Command {
    private static final String HELP_FORMAT = "Fear not! The list of instructions is here:";
    private static final String TODO_FORMAT = "'todo [task]' -->";
    private static final String TODO_INFO = " adds a Todo task to the list";
    private static final String DEADLINE_FORMAT = "'deadline [task] /by [timing]' -->";
    private static final String DEADLINE_INFO = " adds a Deadline task to the list";
    private static final String EVENT_FORMAT = "'event [task] /by [timing]' -->";
    private static final String EVENT_INFO = " adds an Event task to the list";
    private static final String LIST_FORMAT = "'list -->'";
    private static final String LIST_INFO = " shows the tasks in the current list";
    private static final String DONE_FORMAT = "'done [index]' -->";
    private static final String DONE_INFO = " marks a task in the list as done by its index";
    private static final String DELETE_FORMAT = "'delete [index]' -->";
    private static final String DELETE_INFO = " deletes a task from the list by its index";
    private static final String FIND_FORMAT = "'find [keyword]' -->";
    private static final String FIND_INFO = " finds all the tasks in the list that have a certain keyword";
    private static final String BYE_FORMAT = "'bye' -->";
    private static final String BYE_INFO = " closes IntelliBot";
    private static final String HELP_MESSAGE = HELP_FORMAT
            + LINE_BREAK + TODO_FORMAT + TODO_INFO
            + LINE_BREAK + DEADLINE_FORMAT + DEADLINE_INFO
            + LINE_BREAK + EVENT_FORMAT + EVENT_INFO
            + LINE_BREAK + LIST_FORMAT + LIST_INFO
            + LINE_BREAK + DONE_FORMAT + DONE_INFO
            + LINE_BREAK + DELETE_FORMAT + DELETE_INFO
            + LINE_BREAK + FIND_FORMAT + FIND_INFO
            + LINE_BREAK + BYE_FORMAT + BYE_INFO;

    /**
     * Executes the Command and returns a String.
     *
     * @return A String to show to the user after execution of the HelpCommand.
     */
    @Override
    public String execute() {
        return HELP_MESSAGE;
    }
}
