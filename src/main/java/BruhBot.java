import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BruhBot {
    private static final String LOGO = "  /$$                           /$$      \n"
            + " | $$                          | $$      \n" + " | $$$$$$$   /$$$$$$  /$$   /$$| $$$$$$$ \n"
            + " | $$__  $$ /$$__  $$| $$  | $$| $$__  $$\n" + " | $$  \\ $$| $$  \\__/| $$  | $$| $$  \\ $$\n"
            + " | $$  | $$| $$      | $$  | $$| $$  | $$\n" + " | $$$$$$$/| $$      |  $$$$$$/| $$  | $$\n"
            + " |_______/ |__/       \\______/ |__/  |__/";

    private static final String GREETING = String.format("What can\n%s\ndo for you today?", LOGO);
    private static final String FAREWELL = "Bye. Hope to see you again soon!\n";
    private static final String STOP_SIGNAL_STRING = "bye";

    private String userCommand = "";
    private Scanner userCommandScanner = new Scanner(System.in);
    private List<String> tasks = new ArrayList<>();

    /**
     * Initializes the chatbot.
     */
    public void init() {
        greet();
    }

    public void shutdown() {
        userCommandScanner.close();
    }

    /**
     * Begin listening for user input. Terminates when the user says 'bye'.
     */
    public void listen() {
        while (true) {
            userCommand = userCommandScanner.nextLine();
            if (respond(userCommand)) {
                break;
            }
        }
    }

    private boolean respond(String command) {
        boolean isStopCommand = userCommand.equals(STOP_SIGNAL_STRING);
        String response = isStopCommand ? FAREWELL : process(command);
        System.out.println(formatResponse(response));
        return isStopCommand;
    }

    /**
     * Processes the command entered, then returns the appropriate response.
     * 
     * @param command The command entered by the user.
     * @return The response to be displayed.
     */
    private String process(String command) {
        if (command.equals("list")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
            }
            return sb.toString();
        } else {
            tasks.add(command);
            return "added: " + command + '\n';
        }
    }

    private String formatResponse(String content) {
        final String divider = "____________________________________________________________\n";
        String out = divider + content.replaceAll("(?m)^", " ") + divider;
        return out.replaceAll("(?m)^", "    ");
    }

    private void greet() {
        System.out.println(GREETING + '\n');
    }
}
