import java.util.Scanner;

public class Duke {

    /**
     * When run, opens a chat bot that greets the user and echos any user's input. If the input
     * is bye the chat bot is closed.
     * @param args Not used.
     */
    public static void main(String[] args) {
        startUp();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = getUserInput(scanner);
            if (!evaluateUserInput(input)) {
                endChat();
                break;
            }
        }
    }

    private static String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Evaluates a user's input to the chat bot.
     * @param input The user's input.
     * @return False if the user's input is supposed to close the chat bot. Else, the chat bot continues
     * and wait for the user to input another word.
     */
    private static boolean evaluateUserInput(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return false;
        }
        echoUser(input);
        return true;
    }

    private static void echoUser(String text) {
        System.out.println(text + "\n");
    }

    private static void startUp() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm\n" + logo);
        System.out.println("\n What can I help you with? \n\n");
    }

    private static void endChat() {
        System.out.println("Bye!! Hope to see you again!!");
    }
}
