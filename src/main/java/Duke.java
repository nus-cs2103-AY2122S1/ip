import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String ROBOT_EMOJI = "\uD83E\uDD16";
    private static final String COLOR_CYAN = "\u001B[36m";
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String LOGO =
        " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    private static final ArrayList<String> tasks = new ArrayList<>();

    /**
     * Starts the bot
     *
     * @param args CLI commands (not used for now)
     */
    public static void main(String[] args) {
        printLogo();
        greet();
        handleCommands();
    }

    private static void handleCommands() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.nextLine();
            switch (cmd) {
            case "list":
                printAllTasks();
                break;
            case "bye":
                sc.close();
                robotPrint("Bye. Hope to see you again soon!");
                return;
            default:
                addTask(cmd);
            }
        }
    }

    private static void addTask(String task) {
        tasks.add(task);
        robotPrint("added --> " + task);
    }

    private static void printAllTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            robotPrint((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void printLogo() {
        System.out.println(COLOR_CYAN + LOGO + COLOR_RESET);
    }

    private static void greet() {
        robotPrint("Hello! I'm Duke");
        robotPrint("What can I do for you?");
    }

    private static void robotPrint(String string) {
        System.out.println(ROBOT_EMOJI + ": " + string);
    }
}
