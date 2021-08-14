import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputHandler {
    private final String welcome = " Hello! I'm Duke\n What can I do for you?";
    private Database db = new Database();
    private HashMap<String, Function<String, Boolean>> cmds = new HashMap<>();
    private String borders;

    public InputHandler(int borderLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < borderLength; i++) sb.append('_');
        borders = sb.toString();

        cmds.put("bye", (bye) -> {
            System.out.println(formatReply(" Bye. Hope to see you again soon!"));
            return true;
        });
        cmds.put("list", (list) -> {
            System.out.println(formatReply(" Here are the tasks in your list:\n" + db.toString()));
            return false;
        });
        cmds.put("done", args -> {
            try {
                Task t = db.get(Integer.parseInt(args) - 1);
                t.markComplete();
                System.out.println(formatReply(" Nice! I've marked this task as done:\n   " + t));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.err.println(" Enter a valid index.");
            }
            return false;
        });
        cmds.put("todo", x -> {
            Task t = new Todo(x);
            db.add(t);
            System.out.println(formatReply(" Got it. I've added this task:\n   " + t
                    + "\n Now you have " + db.size() + " tasks in the list."));
            return false;
        });
        cmds.put("deadline", x -> {
            List<String> tokens = new Scanner(x).tokens().collect(Collectors.toList());
            String curr = new String(), desc = curr;
            for (String in : tokens) {
                if (in.equals("/by")) {
                    desc = curr;
                    curr = new String();
                } else curr += " " + in;
            }
            Deadline t = new Deadline(desc.substring(1), curr.substring(1));
            db.add(t);
            System.out.println(formatReply(" Got it. I've added this task:\n   " + t
                    + "\n Now you have " + db.size() + " tasks in the list."));
            return false;
        });
        cmds.put("event", x -> {
            List<String> tokens = new Scanner(x).tokens().collect(Collectors.toList());
            String curr = new String(), desc = curr;
            for (String in : tokens) {
                if (in.equals("/at")) {
                    desc = curr;
                    curr = new String();
                } else curr += " " + in;
            }
            Event t = new Event(desc.substring(1), curr.substring(1));
            db.add(t);
            System.out.println(formatReply(" Got it. I've added this task:\n   " + t
                    + "\n Now you have " + db.size() + " tasks in the list."));
            return false;
        });
        cmds.put("add", x -> {
            db.add(new Task(x));
            System.out.println(formatReply(" added: " + x));
            return false;
        });
        System.out.println(formatReply(welcome));
    }

    public boolean query(String input) {
        Scanner sc = new Scanner(input);
        String cmd = sc.next();
        String args = sc.hasNext() ? sc.nextLine().substring(1) : "";
        boolean terminate = false;
        if (cmds.containsKey(cmd)) {
            terminate = cmds.get(cmd).apply(args);
        } else {
            terminate = cmds.get("add").apply(input);
        }
        return !terminate;
    }

    private String formatReply(String input) {
        return borders + "\n" + input + '\n' + borders;
    }

}
