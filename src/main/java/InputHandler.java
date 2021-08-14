import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputHandler {
    private Database db = new Database();
    private HashMap<String, CheckedFunction<String, Record>> cmds = new HashMap<>();

    public InputHandler() {
        cmds.put("greet", this::greet);
        cmds.put("bye", this::bye);
        cmds.put("list", this::list);
        cmds.put("done", this::done);
        cmds.put("todo", this::todo);
        cmds.put("deadline", this::deadline);
        cmds.put("event", this::event);
    }

    public Record query(String input) throws DukeException {
        Scanner sc = new Scanner(input);
        String cmd = sc.next();
        String raw = sc.hasNext() ? sc.nextLine().substring(1) : "";
        if (cmds.containsKey(cmd)) return cmds.get(cmd).apply(raw);
        else throw new DukeException("I'm sorry, but I don't know what that means :-(");
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

    public Record greet(String args) {
        return new Record(" Hello! I'm Duke\n What can I do for you?");
    }

    private Record bye(String args) {
        return new Record(" Bye. Hope to see you again soon!", true);
    }

    private Record list(String args) {
        return new Record(" Here are the tasks in your list:\n" + db.toString());
    }

    private Record done(String args) throws DukeException {
        try {
            Task t = db.get(Integer.parseInt(args) - 1);
            t.markComplete();
            return new Record(" Nice! I've marked this task as done:\n   " + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Enter a valid index.");
        }
    }

    private Record todo(String args) throws DukeException {
        if (args.length() == 0)
            throw new DukeException("The description of a todo cannot be empty.");
        Task t = new Todo(args);
        db.add(t);
        return new Record(" Got it. I've added this task:\n   " + t
                + "\n Now you have " + db.size() + " tasks in the list.");
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
        return new Record(" Got it. I've added this task:\n   " + t
                + "\n Now you have " + db.size() + " tasks in the list.");
    }

    private Record event(String raw) throws DukeException {
        List<String> args = parse(raw, "/at");
        Event t = new Event();
        if (args.size() == 0 || args.get(0).equals(new String()))
            throw new DukeException("The description of a deadline cannot be empty.");
        if (args.size() >= 1)
            t.addDesc(args.get(0));
        if (args.size() == 2)
            t.addTime(args.get(1));
        db.add(t);
        return new Record(" Got it. I've added this task:\n   " + t
                + "\n Now you have " + db.size() + " tasks in the list.");
    }
}
