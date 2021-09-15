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
            + "todo [description] - Adds a todo task\n"
            + "deadline [description] [optional: /by yyyy-mm-dd hh-mm] - Adds a deadline task\n"
            + "event [description] [optional: /at yyyy-mm-dd hh-mm] - Adds a deadline task\n"
            + "list - Lists all the tasks you have currently\n"
            + "done [index] - Marks the task corresponding to that index number as done\n"
            + "delete [index] - Deletes the task corresponding to that index number as done\n"
            + "find [keyword] - Finds the task with the keyword\n"
            + "sort - Sorts the task list in chronological order\n"
            + "bye - Exit JaredDeluxe\n"
            + "____________________________________________________";

}
