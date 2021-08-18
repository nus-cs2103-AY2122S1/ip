import java.util.Scanner;


public class Duke {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String INTRO_MESSAGE = "Hello~ I'm Duke ʕ•ᴥ•ʔ \n" + logo + "\n What can I do for you?";
    private static final String EXIT_MESSAGE = "Goodbyeeee! Hope to see you again soon! ʕっ• ᴥ •ʔっ";
    private static final String EXIT_COMMAND = "bye";
    private static final String SEPARATOR = "------------------------------------";

    private static void printReply(String str) {
        System.out.println("\n" + "DUKE: " + str + "\n" + SEPARATOR);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        printReply(INTRO_MESSAGE);
        String command = sc.nextLine().trim();

        while (!command.equals(EXIT_COMMAND)) {
            printReply(command);
            command = sc.nextLine().trim();
        }

        printReply((EXIT_MESSAGE));
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

}