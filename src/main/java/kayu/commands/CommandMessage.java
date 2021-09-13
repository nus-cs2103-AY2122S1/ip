package kayu.commands;

/**
 * Holds all the possible messages and formats that are required by {@link kayu.commands.Command} classes.
 */
public class CommandMessage {

    // Message formats.
    protected static final String MESSAGE_ITEM_FORMAT = "%d. %s"; // number, and item as string
    protected static final String MESSAGE_EMPTY_TASK_LIST = "List is empty, try adding some tasks first.";
    protected static final String MESSAGE_EMPTY_NOTE_LIST = "List is empty, try adding some notes first.";
    protected static final String MESSAGE_TASK_DONE = "Nice! I've marked this task as done:\n  %s";
    protected static final String MESSAGE_LIST_CONTENTS = "Here are the task(s) in your list:\n";
    protected static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    protected static final String MESSAGE_MATCHING_CONTENTS =
            "Here are the matching task(s) to the expressions(s) in '%s':\n";
    protected static final String MESSAGE_NO_MATCHING_CONTENTS =
            "There are no matching tasks to the expressions(s) in '%s'.";
    protected static final String MESSAGE_DELETED_TASK =
            "Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.";
    protected static final String MESSAGE_DELETED_NOTE =
            "Noted. I've removed this note:\n  %s\nNow you have %d note(s) in the list.";
    protected static final String MESSAGE_CREATED_TODO =
            "Got it. I've added this TODO task:\n  %s\nNow you have %d task(s) in the list.";
    protected static final String MESSAGE_CREATED_EVENT =
            "Got it. I've added this EVENT task:\n  %s\nNow you have %d task(s) in the list.";
    protected static final String MESSAGE_CREATED_DEADLINE =
            "Got it. I've added this DEADLINE task:\n  %s\nNow you have %d task(s) in the list.";
    protected static final String MESSAGE_CREATED_NOTE =
            "Got it. I've added this note:\n  > %s\nNow you have %d note(s) in the list.";

    // Error formats.
    protected static final String ERROR_EMPTY_COMMAND = "Instruction cannot be empty.";
    protected static final String ERROR_NOT_AN_INT_PARAM = "'%s' is not an integer.";
    protected static final String ERROR_IMPROPER_DATE = "Date input is not in the right format.";
    protected static final String ERROR_IMPROPER_TIME = "Time input is not in the right format.";
    protected static final String ERROR_UNKNOWN_COMMAND = "Instruction does not follows specified format.";
    protected static final String ERROR_EMPTY_PARAMS = "Command '%s' requires parameters to execute.";
    protected static final String ERROR_IMPROPER_FORMATTING = "Command '%s' is not formatted properly with key '%s'";

    // Assertion format.
    protected static final String ASSERT_FAIL_NULL_PARAMS = "Parameter String should not be null.";
    protected static final String ASSERT_FAIL_INCOMPLETE_PARAMS = "Parameters extracted are not complete.";
    protected static final String ASSERT_FAIL_NULL_TASK = "Task to update should not be null.";
}
