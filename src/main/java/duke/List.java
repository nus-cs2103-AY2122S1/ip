package duke;

import static java.lang.Integer.parseInt;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the Todo List for Duke.
 */
public class List {
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
     * Adds Task to the List.
     *
     * @param input the command given.
     * @throws IOException If the file cannot be read/found.
     */
    public String addTask(String input, Parser parser, Storage storage) throws IOException {
        if (input.equals("list")) {
            return showList();
        } else {
            try {
                return parser.process(input, this, storage);
            } catch (DukeException e) {
                return (e.getMessage());
            }
        }
    }

    /**
     * Display the current content of the list.
     * Ordered by creation time.
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
     */
    public String done(String[] array) throws DukeDoneException {
        if (array.length == 1) {
            throw new DukeDoneException();
        }
        int index = parseInt(array[1]);
        Task temp = todos.get(index - 1);
        temp.markAsDone();
        return "Nice! I've marked this task as done:\n" + temp;
    }

    /**
     * Deletes the target Task.
     *
     * @param array The input command.
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
}
