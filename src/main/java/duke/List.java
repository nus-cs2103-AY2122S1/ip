package duke;

import static java.lang.Integer.parseInt;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the List of tasks for Duke.
 */
public class List {
    public static final String DISPLAY_LIST_COMMAND = "list";
    public static final String HELP_COMMAND = "help";
    public static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    private ArrayList<Task> todos;

    /**
     * Constructor for List Class when there is no initial data.
     * Creates an empty list.
     */
    public List() {
        this.todos = new ArrayList<>();
    }

    /**
     * Overloaded Constructor for List Class.
     * Used when loading existing data from file.
     * Creates a list consistent with the data given.
     *
     * @param data
     */
    public List(ArrayList<Task> data) {
        this.todos = data;
    }

    public ArrayList<Task> getTodos() {
        return this.todos;
    }

    /**
     * Adds a Task to the List.
     *
     * @param input the command given.
     * @throws IOException If the file cannot be read/found.
     *
     * @returns The response in String after the action is executed.
     */
    public String addTask(String input, Parser parser) throws IOException {
        if (input.equals(DISPLAY_LIST_COMMAND)) {
            return showList();
        }
        if (input.equals(HELP_COMMAND)) {
            return helpPage();
        }
        try {
            return parser.process(input, this);
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }

    /**
     * Displays the current content of the list.
     * Ordered by creation time.
     *
     * @returns The list in String.
     */
    public String showList() {
        String result = "";
        for (int i = 0; i < todos.size(); i++) {
            result += (i + 1 + ". " + todos.get(i).toString() + "\n");
        }
        return result;
    }

    /**
     * Marks the target Task as done.
     *
     * @param array The input command.
     * @throws DukeDoneException If the number of entry is not specified in the command.
     * @returns The response in String after the action is executed.
     */
    public String done(String[] array) throws DukeDoneException {
        if (array.length == 1) {
            throw new DukeDoneException();
        }
        int index = parseInt(array[1]);
        Task temp = todos.get(index - 1);
        temp.markAsDone();
        return DONE_MESSAGE + temp;
    }

    /**
     * Deletes the target Task.
     *
     * @param array The input command.
     * @returns The response in String after the action is executed.
     */
    public String delete(String[] array) {
        int index = parseInt(array[1]);
        Task temp = todos.remove(index - 1);
        return ("Noted. I've removed this task:\n"
                + temp
                + "\nNow you have "
                + todos.size()
                + " task"
                + (todos.size() == 1 ? "" : "s")
                + " in the list");
    }

    /**
     * Prints out the new Task added and the total number of Tasks in the List.
     *
     * @param item The new Task created.
     * @returns The response in String after the action is executed.
     */
    public String echo(Task item) {
        return ("Got it. I've added this task:\n"
                + item
                + "\nNow you have "
                + todos.size()
                + " task"
                + (todos.size() == 1 ? "" : "s")
                + " in the list");
    }

    /**
     * Prints out the search result based on input.
     *
     * @param input The keyword to be searched.
     * @returns The response in String after the action is executed.
     */
    public String search(String input) {
        ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getName().contains(input)) {
                result.add(todos.get(i));
            }
        }
        String text = "Here are the matching tasks in your list:";
        for (int i = 0; i < result.size(); i++) {
            text += ("\n" + (i + 1) + ". " + result.get(i).toString());
        }
        return text;
    }

    /**
     * Displays the help Page.
     *
     * @return Content of the help pgae.
     */
    public String helpPage() {
        return "The available commands are:\n"
                + "todo\n"
                + "event\n"
                + "deadline\n"
                + "list\n"
                + "find\n"
                + "done\n"
                + "delete";
    }

    /**
     * Displays help for each command in more details.
     *
     * @param input the command that needs help.
     * @return details about the command.
     */
    public String moreHelp(String input) {
        if (input.equals("todo")) {
            return Todo.DESCRIPTION + "\n\nFormat:\n" + Todo.FORMAT + "\n\nExample:\n" + Todo.EXAMPLE;
        } else if (input.equals("event")) {
            return Event.DESCRIPTION + "\n\nFormat:\n" + Event.FORMAT + "\n\nExample:\n" + Event.EXAMPLE;
        } else if (input.equals("deadline")) {
            return Deadline.DESCRIPTION + "\n\nFormat:\n" + Deadline.FORMAT + "\n\nExample:\n" + Deadline.EXAMPLE;
        } else if (input.equals("list")) {
            return "Returns a list of all items." + "\n\nFormat:\n" + "list";
        } else if (input.equals("done")) {
            return "Marks a certain item as done" + "\n\nFormat:\n" + "done + number" + "\n\nExample:\n" + "done 1";
        } else if (input.equals("find")) {
            return "Returns all items that contains the keyword"
                    + "\n\nFormat:\n" + "find + keyword" + "\n\nExample:\n" + "find a";
        } else if (input.equals("delete")) {
            return "Delete an item" + "\n\nFormat:\n" + "delete + number" + "\n\nExample:\n" + "delete 1";
        } else {
            return "No such command... ";
        }
    }
}
