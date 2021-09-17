package duke.data.messages;

import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.command.TodoCommand;

/**
 * Class that encapsulates all user-visible messages.
 *
 * @author Wang Hong Yong
 */
public class Messages {

    public static final String MESSAGE_WELCOME_1 = "Hello! I'm Shiba Bot";
    public static final String MESSAGE_WELCOME_2 = "What can I do for you? \n \n"
            + DeadlineCommand.COMMAND_USAGE + DeadlineCommand.COMMAND_FORMAT + "\n"
            + DeleteCommand.COMMAND_USAGE + DeleteCommand.COMMAND_FORMAT + "\n"
            + DoneCommand.COMMAND_USAGE + DoneCommand.COMMAND_FORMAT + "\n"
            + EventCommand.COMMAND_USAGE + EventCommand.COMMAND_FORMAT + "\n"
            + FindCommand.COMMAND_USAGE + FindCommand.COMMAND_FORMAT + "\n"
            + ListCommand.COMMAND_USAGE + ListCommand.COMMAND_FORMAT + "\n"
            + TagCommand.COMMAND_USAGE + TagCommand.COMMAND_FORMAT + "\n"
            + TodoCommand.COMMAND_USAGE + TodoCommand.COMMAND_FORMAT + "\n";
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_EMPTY_DEADLINE = "The description and timing of a deadline"
            + " cannot be empty.";
    public static final String MESSAGE_EMPTY_TODO = "The description of a todo cannot be empty.";

    public static final String MESSAGE_EMPTY_EVENT = "The description and timing of a event cannot be empty."
            + " cannot be empty.";
    public static final String MESSAGE_EMPTY_DELETE = "There must be something to be deleted.";
    public static final String MESSAGE_EMPTY_DONE = "There must be something to be completed.";
    public static final String MESSAGE_EMPTY_FIND = "Please give some input in order to find.";
    public static final String MESSAGE_INPUT_UNKNOWN = "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_IO_ERR = "IOException";
    public static final String MESSAGE_ADD = "     Got it. I've added this task:";
    public static final String MESSAGE_TAG = "     Got it. I've tagged this task:";
    public static final String MESSAGE_ADD_TAG = "     Got it. I've added this tag:";
    public static final String MESSAGE_TASKS_LEFT = "     Now you have %d tasks in the list.";
    public static final String MESSAGE_REMOVE = "     Noted. I've removed this task:";
    public static final String MESSAGE_DONE = "Nice! I've marked this task as done:";
    public static final String MESSAGE_TASK_ALREADY_DONE = "Task is already marked as done";
    public static final String MESSAGE_NO_TASKS_FOUND = "OOPS!!! I could not find any tasks with that word :-(";
    public static final String MESSAGE_DEADLINE_FORMAT = "Hmm.. something is wrong with the format. \n"
            + DeadlineCommand.COMMAND_FORMAT;
    public static final String MESSAGE_DELETE_FORMAT = "Hmm.. something is wrong with the format. \n"
            + DeleteCommand.COMMAND_FORMAT;
    public static final String MESSAGE_DONE_FORMAT = "Hmm.. something is wrong with the format. \n"
            + DoneCommand.COMMAND_FORMAT;
    public static final String MESSAGE_EVENT_FORMAT = "Hmm.. something is wrong with the format. \n"
            + EventCommand.COMMAND_FORMAT;
    public static final String MESSAGE_TAG_FORMAT = "Hmm.. something is wrong with the format. \n"
            + TagCommand.COMMAND_FORMAT;
    public static final String MESSAGE_TODO_FORMAT = "Hmm.. something is wrong with the format. \n"
            + TodoCommand.COMMAND_FORMAT;
    public static final String MESSAGE_TASK_NEGATIVE = "Hello... Please use a positive number >:(";
    public static final String MESSAGE_TASK_NOT_FOUND = "Are you sure this task exist? We cant find it :(";
    public static final String MESSAGE_LIST_EMPTY = "List is empty, please add some tasks before you proceed with the"
            + " command";

}
