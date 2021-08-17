import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String LINE_SPLIT =
            "____________________________________________________________";

    private final static int MAX_STORAGE = 100;

    private final static List<String> TODO_LIST = new ArrayList<>();

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
            addToList(userInput);
            userInput = SCANNER.nextLine();
            userInput = userInput.trim();
        }
        exit();
    }

    public static void echo(String command) {
        System.out.println(LINE_SPLIT);
        System.out.println('\t' + command);
        System.out.println(LINE_SPLIT + '\n');
    }

    public static void addToList(String action) {
        if (TODO_LIST.size() == MAX_STORAGE) {
            echo("unable to add: Max storage in your list!");
            return;
        }
        TODO_LIST.add(action);
        echo("added: ".concat(action));
    }

    public static void exit() {
        echo("Bye. Hope to see you again soon!");
    }
}
