import java.util.Scanner;

public class Duke {

    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String LINE_SPLIT =
            "____________________________________________________________";

    private final static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        printLogo();
        greet();
        run();
    }

    public static void printLogo() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE_SPLIT);
    }

    public static void greet() {
        System.out.println('\t' + "Hello! I'm Duke");
        System.out.println('\t' + "What can I do for you?");
        System.out.println(LINE_SPLIT + '\n');
    }

    public static void run() {
        String userInput = SCANNER.nextLine();
        userInput = userInput.trim();
        while (!userInput.equals("bye")) {
            echo(userInput);
            userInput = SCANNER.nextLine().trim();
        }
        exit();
    }

    public static void echo(String command) {
        System.out.println(LINE_SPLIT);
        System.out.println('\t' + command);
        System.out.println(LINE_SPLIT + '\n');
    }

    public static void exit() {
        echo("Bye. Hope to see you again soon!");
    }
}
