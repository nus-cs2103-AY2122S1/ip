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
        commandList.put("deadline", this::deadline);
        commandList.put("event", this::event);
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

    private String getSizeMsg() {
        return "\n\t Now you have " + database.size() + String.format(" task%sin the list.", database.size() != 1 ? "s " : " ");
    }

    private Record greet(String args) {
        return new Record("Hello! I'm Duke\n\t What can I do for you?" +
                "\n\t (Tip: type help [COMMAND] to get help with my functions!)");
    }

    private boolean verify() {
        System.out.println("WARNING: This procedure is irreversible." + "\n\t Are you sure about proceeding? [y/n]");
        Scanner sc = new Scanner(System.in);
        char response = sc.hasNext() ? sc.next().charAt(0) : 'x';
        while (response != 'y' || response != 'n') {
            System.out.println("Are you sure about proceeding? [y/n]");
            response = sc.hasNext() ? sc.next().charAt(0) : 'x';
        }
        return response == 'y';
    }

    private Record bye(String args) throws DukeException {
        database.close();
        return new Record("Bye. Hope to see you again soon!", true);
    }

    private Record list(String args) {
        if (database.size() == 0) {
            return new Record("You have no tasks!");
        }
        return new Record("Here are the tasks in your list:\n " + database.toString());
    }

    private Record done(String args) throws DukeException {
        if (database.size() == 0) {
            throw new DukeException("You have no tasks.");
        }
        try {
            Task t = database.markAsDone(Integer.parseInt(args) - 1);
            return new Record("Nice! I've marked this task as done:\n\t   " + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Enter a valid index (from 1 to %d).", database.size()));
        }
    }

    private Record delete(String args) throws DukeException {
        try {
            Task t = database.delete(Integer.parseInt(args) - 1);
            return new Record("Noted. I've removed this task:\n\t   " + t + getSizeMsg());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            if (database.size() == 0) {
                throw new DukeException("You have no tasks.");
            }
            throw new DukeException(String.format("Enter a valid index (from 1 to %d).", database.size()));
        }
    }

    private Record todo(String args) throws DukeException {
        if (args.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task t = new Todo(args);
        database.add(t);
        return new Record("Got it. I've added this task:\n\t   " + t + getSizeMsg());
    }

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
        return new Record("Got it. I've added this task:\n\t   " + t + getSizeMsg());
    }

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
        return new Record("Got it. I've added this task:\n\t   " + t + getSizeMsg());
    }

    private Record help(String raw) throws DukeException {
        if (raw.equals(new String())) {
            return new Record(Ui.help());
        } else if (commandList.containsKey(raw)) {
            return new Record(Ui.help(raw));
        } else {
            throw new DukeException("This command isn't in my books!");
        }
    }

    private Record clear(String raw) throws DukeException {
        database.clear();
        return new Record("Task list was cleared.");
        /*
        if (verify()) {
            db.clear();
            return new Record("Task list was cleared.");
        }
        else return new Record("No worries! No changes were made.");
         */
    }

    private Record find(String raw) throws DukeException {
        TaskList filtered = database.find(raw);
        if (filtered.size() == 0) {
            return new Record("No matching tasks found.");
        }
        return new Record("Here are the matching tasks in your list:\n " + filtered);
    }
}