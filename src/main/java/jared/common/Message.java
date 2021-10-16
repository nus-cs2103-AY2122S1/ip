package jared.common;

/**
 * Container for user visible messages.
 */
public class Message {
    public static final String MESSAGE_WELCOME = "____________________________________________________\n"
            + "Hello! I'm Jared\n"
            + "What can I do for you?\n"
            + "____________________________________________________";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    public static final String MESSAGE_HELP = "____________________________________________________\n"
            + "Welcome to JaredDeluxe\n"
            + "Here are the list of commands available\n"
            + "todo DESCRIPTION - Adds a todo task\n"
            + "deadline DESCRIPTION /by [DATE] [TIME] - Adds a deadline task\n"
            + "event DESCRIPTION /at [DATE] [TIME] - Adds a deadline task\n"
            + "list - Lists all the tasks you have currently\n"
            + "done INDEX - Marks the task corresponding to that index number as done\n"
            + "delete INDEX - Deletes the task corresponding to that index number as done\n"
            + "find KEYWORD - Finds the task with the keyword\n"
            + "sort - Sorts the task list in chronological order\n"
            + "bye - Exit JaredDeluxe\n"
            + "____________________________________________________";

}
