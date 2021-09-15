package ponyo.common;

public class Messages {
    public static final String MESSAGE_WELCOME = "Hello, it's Ponyo!\nHow can I help you today?";
    public static final String MESSAGE_INSTRUCTIONS = "Here are the list of commands we support:\n"
            + "Add a todo:\t  todo / t [task]\n"
            + "...or a deadline: deadline / d [t] /by [date]\n"
            + "...or an event:\t  event / e [ev] /at [date]\n"
            + "List all tasks:\t  list / l\n"
            + "Mark task done:\t  done [id]\n"
            + "Delete a task:\t  delete / del [id]\n"
            + "Locate task:\t  find / f [name]\n"
            + "Exit the program: exit / bye";
    public static final String MESSAGE_IDK = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_INVALID = "Invalid command given!";
    public static final String MESSAGE_INVALID_TODO = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String MESSAGE_INVALID_DEADLINE = "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String MESSAGE_INVALID_EVENT = "☹ OOPS!!! The description of an event cannot be empty.";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "☹ OOPS!!! Please use the date format: yyyy-mm-dd.";
    public static final String MESSAGE_NO_TASKS = "There are no tasks in your list! "
            + "Start adding one with: todo, deadline or event.";
    public static final String MESSAGE_NO_MATCHING_TASKS = "There are no matching tasks in your list.";
    public static final String MESSAGE_ERROR_LOADING_FILE = "Error loading from file :(";
}
