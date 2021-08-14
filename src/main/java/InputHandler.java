import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

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
        cmds.put("add", x -> {
            System.out.println(formatReply(" added: " + x));
            db.add(new Task(x));
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
