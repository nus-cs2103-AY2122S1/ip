import java.util.*;
import java.util.stream.Collectors;

public class InputHandler {
    private Database db = new Database();
    private HashMap<String, CheckedFunction<String, Record>> cmds = new HashMap<>();

    public InputHandler() {
        cmds.put("greet", this::greet);
        cmds.put("bye", this::bye);
        cmds.put("list", this::list);
        cmds.put("done", this::done);
        cmds.put("delete", this::delete);
        cmds.put("todo", this::todo);
        cmds.put("deadline", this::deadline);
        cmds.put("event", this::event);
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

    public List<String> parse(String raw, String separator) {
        List<String> tokens = new Scanner(raw).tokens().collect(Collectors.toList());
        List<String> args = new ArrayList<>();
        if (tokens.size() == 0) return args;
        String curr = new String();
        for (String in : tokens) {
            if (in.equals(separator)) {
                args.add(curr);
                curr = new String();
            } else curr += " " + in;
        }
        args.add(curr);
        return args;
    }

    public String sizeMsg() {
        return "\n\t Now you have " + db.size() + String.format(" task%sin the list.", db.size() > 1 ? "s " : " ");
    }

    public Record greet(String args) {
        return new Record("Hello! I'm Duke\n\t What can I do for you?");
    }

    private Record bye(String args) {
        return new Record("Bye. Hope to see you again soon!", true);
    }

    private Record list(String args) {
        return new Record("Here are the tasks in your list:\n " + db.toString());
    }

    private Record done(String args) throws DukeException {
        try {
            Task t = db.get(Integer.parseInt(args) - 1);
            t.markComplete();
            return new Record("Nice! I've marked this task as done:\n\t   " + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            if (db.size() == 0) throw new DukeException("You have no tasks.");
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
        List<String> args = parse(raw, "/by");
        Deadline t = new Deadline();
        if (args.size() == 0 || args.get(0).equals(new String()))
            throw new DukeException("The description of a deadline cannot be empty.");
        if (args.size() >= 1)
            t.addDesc(args.get(0));
        if (args.size() == 2)
            t.addDeadline(args.get(1));
        db.add(t);
        return new Record("Got it. I've added this task:\n\t   " + t + sizeMsg());
    }

    private Record event(String raw) throws DukeException {
        List<String> args = parse(raw, "/at");
        Event t = new Event();
        if (args.size() == 0 || args.get(0).equals(new String()))
            throw new DukeException("The description of a event cannot be empty.");
        if (args.size() >= 1)
            t.addDesc(args.get(0));
        if (args.size() == 2)
            if (db.size() == 0) throw new DukeException("You have no tasks.");
            t.addTime(args.get(1));
        db.add(t);
        return new Record("Got it. I've added this task:\n\t   " + t + sizeMsg());
    }
}
