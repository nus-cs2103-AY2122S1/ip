import java.util.HashMap;
import java.util.function.Function;

public class InputHandler {
    private final String welcome = " Hello! I'm Duke\n What can I do for you?";
    private final String termination = " Bye. Hope to see you again soon!";
    private Database db = new Database();
    private HashMap<String, Function<String, Boolean>> cmds = new HashMap<>();
    private String borders;

    public InputHandler(int borderLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < borderLength; i++) sb.append('_');
        borders = sb.toString();

        cmds.put("bye", (bye) -> {
            System.out.println(formatReply(termination));
            return true;
        });
        cmds.put("list", (list) -> {
            System.out.println(formatReply(db.toString()));
            return false;
        });
        cmds.put("add", x -> {
            System.out.println(formatReply(" added: " + x));
            db.add(x);
            return false;
        });
        System.out.println(formatReply(welcome));
    }

    public boolean query(String input) {
        boolean terminate = false;
        if (cmds.containsKey(input)) {
            terminate = cmds.get(input).apply(input);
        } else {
            terminate = cmds.get("add").apply(input);
        }
        return !terminate;
    }

    private String formatReply(String input) {
        return borders + "\n" + input + '\n' + borders;
    }

}
