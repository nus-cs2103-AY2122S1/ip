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
    
    private TaskList db;
    private HashMap<String, CheckedFunction<String, Record>> cmds = new HashMap<>();

    public Parser() throws DukeException {
        db = new TaskList();
        cmds.put("greet", this::greet);
        cmds.put("bye", this::bye);
        cmds.put("list", this::list);
        cmds.put("done", this::done);
        cmds.put("delete", this::delete);
        cmds.put("todo", this::todo);
        cmds.put("deadline", this::deadline);
        cmds.put("event", this::event);
        cmds.put("help", this::help);
    }

    public Record query(String input) throws DukeException {
        try {
            Scanner sc = new Scanner(input);
            String cmd = sc.next();
            String raw = sc.hasNext() ? sc.nextLine().substring(1) : "";
            if (cmds.containsKey(cmd)) return cmds.get(cmd).apply(raw);
            else throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } catch (NoSuchElementException e) {
            throw new DukeException("Please enter a command.");
        }
    }

    public String sizeMsg() {
        return "\n\t Now you have " + db.size() + String.format(" task%sin the list.", db.size() != 1 ? "s " : " ");
    }

    public Record greet(String args) {
        return new Record("Hello! I'm duke.Duke\n\t What can I do for you?");
    }

    private Record bye(String args) throws DukeException {
        db.close();
        return new Record("Bye. Hope to see you again soon!", true);
    }

    private Record list(String args) {
        if (db.size() == 0) return new Record("You have no tasks!");
        return new Record("Here are the tasks in your list:\n " + db.toString());
    }

    private Record done(String args) throws DukeException {
        if (db.size() == 0) throw new DukeException("You have no tasks.");
        try {
            Task t = db.markAsDone(Integer.parseInt(args) - 1);
            return new Record("Nice! I've marked this task as done:\n\t   " + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Enter a valid index (from 1 to %d).", db.size()));
        }
    }

    private Record delete(String args) throws DukeException {
        try {
            Task t = db.delete(Integer.parseInt(args) - 1);
            return new Record("Noted. I've removed this task:\n\t   " + t + sizeMsg());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            if (db.size() == 0) throw new DukeException("You have no tasks.");
            throw new DukeException(String.format("Enter a valid index (from 1 to %d).", db.size()));
        }
    }

    private Record todo(String args) throws DukeException {
        if (args.length() == 0)
            throw new DukeException("The description of a todo cannot be empty.");
        Task t = new Todo(args);
        db.add(t);
        return new Record("Got it. I've added this task:\n\t   " + t + sizeMsg());
    }

    private Record deadline(String raw) throws DukeException {
        String[] args = raw.split("( /by )");
        Deadline t = new Deadline();
        if (args.length == 0 || args[0].equals(new String()))
            throw new DukeException("The description of a deadline cannot be empty.");
        if (args.length == 2) {
            t.addDesc(args[0]);
            t.addTime(args[1]);
            db.add(t);
        } else if (args.length == 1)
            throw Task.FORMAT_EXCEPTION;
        return new Record("Got it. I've added this task:\n\t   " + t + sizeMsg());
    }

    private Record event(String raw) throws DukeException {
        String[] args = raw.split("( /at )");
        Event t = new Event();
        if (args.length == 0 || args[0].trim().equals(new String()))
            throw new DukeException("The description of a event cannot be empty.");
        if (args.length == 2) {
            t.addDesc(args[0]);
            t.addTime(args[1]);
            db.add(t);
        } else if (args.length == 1)
            throw Task.FORMAT_EXCEPTION;
        return new Record("Got it. I've added this task:\n\t   " + t + sizeMsg());
    }
    
    private Record help(String raw) throws DukeException {
        return new Record(Ui.help());
    }
}