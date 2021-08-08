import java.util.Scanner;

public class Duke {
    public static final String SPACE = "    ";
    public static final String LOGO = " ____        _        \n"
            + SPACE + "|  _ \\ _   _| | _____ \n"
            + SPACE + "| | | | | | | |/ / _ \\\n"
            + SPACE + "| |_| | |_| |   <  __/\n"
            + SPACE + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String BOT_LINE = "============================================================";
    public static final String USER_LINE = "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _";
    private static final String GREETINGS = "Yo! I'm Duke\nWhat can I do for you?";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(SPACE + BOT_LINE);
        System.out.println(SPACE + "Hello from\n" + SPACE +  LOGO);
        System.out.println(SPACE + BOT_LINE);
        System.out.println(SPACE + "Yo! I'm Duke");
        System.out.println(SPACE + "What can I do for you?");
        System.out.println(SPACE + BOT_LINE);

        String input = getInput(sc);
        while (!input.equals("bye")) {
            showMessage(input);
            input = getInput(sc);
        }
        showMessage("Bye. Have a good time!");
    }

    private static String getInput(Scanner sc) {
        return sc.nextLine();
    }

    private static void showMessage(String message) {
        message = SPACE + message.replace("\n", "\n" + SPACE);
        System.out.println(SPACE + USER_LINE);
        System.out.println(message);
        System.out.println("");
        System.out.println(SPACE + BOT_LINE);
    }
}
