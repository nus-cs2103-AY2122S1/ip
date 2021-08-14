import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ENDING_COMMAND = "bye";

    private static void say(String message) {
        System.out.println("Iris: " + message);
    }

    private static String prompt() {
        System.out.print("me: ");
        return scanner.nextLine();
    }

    private static void echo(String message) {
        say(message);
    }

    public static void main(String[] args) {
        say("Hello! I'm Iris. What can I do for you?");
        String command = prompt();
        while (!command.equals(ENDING_COMMAND)) {
            echo(command);
            command = prompt();
        }

        say("Bye. Hope to see you again soon!");
    }
}
