import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static String formatDukeResponse(String response) {
        return HORIZONTAL_LINE + "\n" + response + "\n" + HORIZONTAL_LINE + "\n";
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = logo + "Hello! I'm Duke.\n" + "What can I do for you";
        System.out.println(formatDukeResponse(welcomeMessage));
    }

    public static void printExitMessage() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(formatDukeResponse(exitMessage));
    }

    public static void printEchoMessage(String message) {
        System.out.println(formatDukeResponse(message));
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                sc.close();
                break;
            }
            printEchoMessage(userInput);
        }

        printExitMessage();
    }
}
