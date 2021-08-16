import java.util.Scanner;

public class BruhBot {
    private static final String LOGO = "  /$$                           /$$      \n"
            + " | $$                          | $$      \n" + " | $$$$$$$   /$$$$$$  /$$   /$$| $$$$$$$ \n"
            + " | $$__  $$ /$$__  $$| $$  | $$| $$__  $$\n" + " | $$  \\ $$| $$  \\__/| $$  | $$| $$  \\ $$\n"
            + " | $$  | $$| $$      | $$  | $$| $$  | $$\n" + " | $$$$$$$/| $$      |  $$$$$$/| $$  | $$\n"
            + " |_______/ |__/       \\______/ |__/  |__/";

    private static final String GREETING = String.format("What can\n%s\ndo for you today?", LOGO);
    private static final String FAREWELL = "Bye. Hope to see you again soon!";
    private static final String STOP_SIGNAL_STRING = "bye";

    private String userCommand = "";
    private Scanner userCommandScanner = new Scanner(System.in);

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
        String response = (isStopCommand ? FAREWELL : command) + '\n';
        System.out.println(formatResponse(response));
        return isStopCommand;
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
