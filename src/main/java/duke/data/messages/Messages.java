package duke.data.messages;

import duke.command.ListCommand;
import duke.command.ByeCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.DeadlineCommand;
import duke.command.DoneCommand;
import duke.command.ToDoCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;

/**
 * Class that encapsulates all user-visible messages.
 *
 * @author Won Ye Ji
 */
public class Messages {
    public static final String INDENTATION = "    ";
    public static final String WELCOME_MESSAGE = "Hi! I'm Jacky, your personal task manager.";
    public static final String BYE_MESSAGE = "Bye! Hope to see you again soon :-)";
    public static final String UNKNOWN_INPUT_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MISSING_DATE = "OOPS!!! Please add a valid date in the format dd/MM/yy HHmm.";
    public static final String PRINT_LIST_MESSAGE = "Here are the tasks in your list:\n";
    public static final String NUMBER_OF_TASKS_MESSAGE = "Now you have %d tasks in the list.\n";
    public static final String MARK_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    public static final String ADD_TASK = "Got it. I've added this task:\n";
    public static final String DELETE_TASK = "Noted. I've removed this task:\n";
    public static final String EMPTY_LIST = "You haven't added anything to your list!";
    public static final String MATCHING_TASK_FOUND = "Here are the matching tasks in your list:\n";
    public static final String NO_MATCHING_TASK_FOUND = "OOPS!!! There are no tasks with that keyword! :-(";
    public static final String EMPTY_TODO_DESC = "OOPS!!! The description of a todo cannot be empty.";
    public static final String EMPTY_DEADLINE_DESC = "OOPS!!! The description of a deadline cannot be empty.";
    public static final String EMPTY_EVENT_DESC = "OOPS!!! The description of an event cannot be empty.";
    public static final String EMPTY_FIND_CMD = "OOPS!!! The description of a find command cannot be empty.";
    public static final String HELP = "Here is a quick guide on how to use me!\n"
            + "1. " + ToDoCommand.MESSAGE_USAGE + "\n"
            + "2. " + EventCommand.MESSAGE_USAGE + "\n"
            + "3. " + DeadlineCommand.MESSAGE_USAGE + "\n"
            + "4. " + DoneCommand.MESSAGE_USAGE + "\n"
            + "5. " + DeleteCommand.MESSAGE_USAGE + "\n"
            + "6. " + ListCommand.MESSAGE_USAGE + "\n"
            + "7. " + FindCommand.MESSAGE_USAGE + "\n"
            + "8. " + ByeCommand.MESSAGE_USAGE + "\n"
            + "9. " + HelpCommand.MESSAGE_USAGE;
}
