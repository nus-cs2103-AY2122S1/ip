package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.utils.CheckedFunction;
import duke.utils.DukeException;
import duke.utils.Record;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {

    private final TaskList database;
    private final HashMap<String, CheckedFunction<String, Record>> commandList = new HashMap<>();

    /**
     * Creates a Parser which initializes a TaskList.
     *
     * @param loadFromStorage Inform the TaskList whether to load tasks from storage.
     * @throws DukeException
     */
    public Parser(boolean loadFromStorage) throws DukeException {
        this(new TaskList(loadFromStorage));
    }

    public Parser(boolean loadFromStorage, String fileName) throws DukeException {
        this(new TaskList(loadFromStorage, fileName));
    }

    /**
     * Creates a Parser with TaskList db and with the recognized commands.
     *
     * @param database TaskList to be added.
     * @throws DukeException
     */
    public Parser(TaskList database) throws DukeException {
        this.database = database;
        commandList.put("greet", this::greet);
        commandList.put("bye", this::bye);
        commandList.put("list", this::list);
        commandList.put("done", this::done);
        commandList.put("delete", this::delete);
        commandList.put("todo", this::todo);
        commandList.put("t", this::todo);
        commandList.put("deadline", this::deadline);
        commandList.put("d", this::deadline);
        commandList.put("event", this::event);
        commandList.put("e", this::event);
        commandList.put("help", this::help);
        commandList.put("clear", this::clear);
        commandList.put("find", this::find);
    }

    /**
     * Takes in a command, and if command is found, passes to it the remaining arguments.
     *
     * @param input
     * @return
     * @throws DukeException
     */
    public Record query(String input) throws DukeException {
        try {
            Scanner sc = new Scanner(input);
            String cmd = sc.next();
            String raw = sc.hasNext() ? sc.nextLine().substring(1) : "";
            if (commandList.containsKey(cmd)) {
                return commandList.get(cmd).apply(raw);
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (NoSuchElementException e) {
            throw new DukeException("Please enter a command.");
        }
    }

    /**
     * Helper function to generate the message for the number of tasks.
     * @return message with number of tasks.
     */
    private String getSizeMsg() {
        return "\n Now you have " + database.size() + String.format(" task%sin the list.", database.size() != 1 ? "s " : " ");
    }

    /**
     * Greeter function.
     * @param args not used.
     * @return greeting.
     */
    private Record greet(String args) {
        String logo = "____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String tag = "Hello from\n " + logo;
        return new Record(tag + "\n What can I do for you?" +
                "\n (Tip: type help [COMMAND] to get help with my functions!)");
    }

    /**
     * Exiting function.
     * @param args not used.
     * @return goodbye.
     * @throws DukeException
     */
    private Record bye(String args) throws DukeException {
        database.close();
        return new Record("Bye. Hope to see you again soon!", true);
    }

    /**
     * Lists all tasks.
     * @param args not used.
     * @return message with string of database.
     */
    private Record list(String args) {
        if (database.size() == 0) {
            return new Record("You have no tasks!");
        }
        return new Record("Here are the tasks in your list:\n" + database.toString());
    }

    /**
     * Marks task as done.
     * @param args index of the target task.
     * @return confirmation message.
     * @throws DukeException
     */
    private Record done(String args) throws DukeException {
        if (database.size() == 0) {
            throw new DukeException("You have no tasks.");
        }
        try {
            Task t = database.markAsDone(Integer.parseInt(args) - 1);
            return new Record("Nice! I've marked this task as done:\n" + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Enter a valid index (from 1 to %d).", database.size()));
        }
    }

    /**
     * Deletes a task.
     * @param args index of target task.
     * @return confirmation message.
     * @throws DukeException
     */
    private Record delete(String args) throws DukeException {
        try {
            Task t = database.delete(Integer.parseInt(args) - 1);
            return new Record("Noted. I've removed this task:\n" + t + getSizeMsg());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            if (database.size() == 0) {
                throw new DukeException("You have no tasks.");
            }
            throw new DukeException(String.format("Enter a valid index (from 1 to %d).", database.size()));
        }
    }

    /**
     * Creates a to-do task based on the description in the query.
     * @param args query with description.
     * @return confirmation message.
     * @throws DukeException
     */
    private Record todo(String args) throws DukeException {
        if (args.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task t = new Todo(args);
        database.add(t);
        return new Record("Got it. I've added this task:\n" + t + getSizeMsg());
    }

    /**
     * Creates a deadline task based on the description and time in the query.
     * @param raw query with description.
     * @return confirmation message.
     * @throws DukeException
     */
    private Record deadline(String raw) throws DukeException {
        String[] args = raw.split("( /by )");
        Deadline t = new Deadline();
        if (args.length == 0 || args[0].equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        if (args.length == 2) {
            t.addDesc(args[0]);
            t.addTime(args[1]);
            database.add(t);
        } else if (args.length == 1) {
            throw Task.FORMAT_EXCEPTION;
        }
        return new Record("Got it. I've added this task:\n" + t + getSizeMsg());
    }

    /**
     * Creates an event task based on the description and time in the query.
     * @param raw query with description.
     * @return confirmation message.
     * @throws DukeException
     */
    private Record event(String raw) throws DukeException {
        String[] args = raw.split("( /at )");
        Event t = new Event();
        if (args.length == 0 || args[0].trim().equals("")) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        if (args.length == 2) {
            t.addDesc(args[0]);
            t.addTime(args[1]);
            database.add(t);
        } else if (args.length == 1) {
            throw Task.FORMAT_EXCEPTION;
        }
        return new Record("Got it. I've added this task:\n" + t + getSizeMsg());
    }

    /**
     * Prints the help message (located in Ui).
     * @param raw particular command to search for.
     * @return relevant help message.
     * @throws DukeException
     */
    private Record help(String raw) throws DukeException {
        if (raw.equals(new String())) {
            return new Record(Ui.help());
        } else if (commandList.containsKey(raw)) {
            return new Record(Ui.help(raw));
        } else {
            throw new DukeException("This command isn't in my books!");
        }
    }

    /**
     * Clears the database.
     * @param raw not used.
     * @return confirmation message.
     * @throws DukeException
     */
    private Record clear(String raw) throws DukeException {
        database.clear();
        return new Record("Task list was cleared.");
    }

    /**
     * Finds the relevant string in the tasks.
     * @param raw string query.
     * @return listing of relevant tasks.
     * @throws DukeException
     */
    private Record find(String raw) throws DukeException {
        TaskList filtered = database.find(raw);
        if (filtered.size() == 0) {
            return new Record("No matching tasks found.");
        }
        return new Record("Here are the matching tasks in your list:\n" + filtered);
    }
}