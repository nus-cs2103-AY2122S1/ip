package sun.data.messages;

import sun.command.ArchiveCommand;
import sun.command.ArchivesCommand;
import sun.command.ByeCommand;
import sun.command.DeadlineCommand;
import sun.command.DeleteCommand;
import sun.command.DoneCommand;
import sun.command.EventCommand;
import sun.command.FindCommand;
import sun.command.HelpCommand;
import sun.command.ListCommand;
import sun.command.ToDoCommand;

/**
 * Class that encapsulates all user-visible messages.
 *
 * @author Won Ye Ji
 */
public class Messages {
    public static final String INDENTATION = "    ";
    public static final String WELCOME_MESSAGE = "Welcome back, I'm Sun. How can I help you today?";
    public static final String BYE_MESSAGE = "Til we meet again, bye!";
    public static final String UNKNOWN_INPUT_MESSAGE = "Sorry, I didn't get that, please enter a valid command.";
    public static final String MISSING_DATE = "Please include the date.";
    public static final String PRINT_LIST_MESSAGE = "Okay, here are your current tasks:\n";
    public static final String PRINT_ARCHIVES_MESSAGE = "These are the tasks in your archives:\n";
    public static final String NUMBER_OF_TASKS_MESSAGE = "Now you have %d tasks left.\n";
    public static final String MARK_TASK_AS_DONE_MESSAGE = "Got it. I've marked this task as done:\n";
    public static final String ADD_TASK = "Got it. I've added this task:\n";
    public static final String ARCHIVE_TASK = "Got it. I've archived this task:\n";
    public static final String DELETE_TASK = "Got it. I've removed this task:\n";
    public static final String EMPTY_LIST = "Your list is empty.";
    public static final String MATCHING_TASK_FOUND = "These are the matching tasks I've found:\n";
    public static final String NO_MATCHING_TASK_FOUND = "There seems to be no tasks with that keyword.";
    public static final String EMPTY_TODO_DESC = "Please enter a valid todo command in the format "
        + "'todo <task description>'. (e.g todo homework)";
    public static final String EMPTY_DEADLINE_DESC = "Please enter a valid deadline command in the format "
        + "'deadline <task description> /by ddMMyy HHmm'. (e.g deadline essay /by 120421 2359)";
    public static final String EMPTY_EVENT_DESC = "Please enter a valid event command in the format "
        + "'event <event description> /at ddMMyy HHmm'. (e.g event party /at 120421 1700)";
    public static final String EMPTY_FIND_CMD = "Please enter a valid keyword.";
    public static final String DATE_FORMAT_ERROR = "Please add a valid date in the format ddMMyy HHmm.";
    public static final String ARCHIVE_FORMAT_ERROR = "Please enter your command in the "
        + "'archive <index>' format. (e.g archive 1)";
    public static final String DONE_FORMAT_ERROR = "Please enter your command in the "
        + "'done <index>' format. (e.g done 1)";
    public static final String DELETE_FORMAT_ERROR = "Please enter your command in the "
        + "'delete <index>' format. (e.g delete 1)";
    public static final String DELETE_MISSING_INDEX_ERROR = "Please choose a task to delete!";
    public static final String ARCHIVE_MISSING_INDEX_ERROR = "Please choose a task to archive!";
    public static final String DONE_MISSING_INDEX_ERROR = "Please choose a task to mark as done!";
    public static final String DELETE_NO_TASKS_ERROR = "There are no tasks to delete.";
    public static final String ARCHIVE_NO_TASKS_ERROR = "There are no tasks to archive.";
    public static final String DONE_NO_TASKS_ERROR = "There are no tasks to mark as done.";
    public static final String INDEX_OUT_OF_BOUNDS_ERROR = "Please enter a value from 1 to %d";
    public static final String ALREADY_MARKED_AS_DONE_ERROR = "Task has already been marked as completed!";
    public static final String HELP = "Here are the commands I can handle:\n"
        + ToDoCommand.MESSAGE_USAGE + "\n"
        + EventCommand.MESSAGE_USAGE + "\n"
        + DeadlineCommand.MESSAGE_USAGE + "\n"
        + DoneCommand.MESSAGE_USAGE + "\n"
        + DeleteCommand.MESSAGE_USAGE + "\n"
        + ListCommand.MESSAGE_USAGE + "\n"
        + FindCommand.MESSAGE_USAGE + "\n"
        + ArchiveCommand.MESSAGE_USAGE + "\n"
        + ByeCommand.MESSAGE_USAGE + "\n"
        + ArchivesCommand.MESSAGE_USAGE + "\n"
        + HelpCommand.MESSAGE_USAGE;
}
