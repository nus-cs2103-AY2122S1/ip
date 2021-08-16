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
    private static final ArrayList<Task> tasks = new ArrayList<>();

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
            String input = sc.nextLine();
            String[] splitInput = input.split(" ");
            String cmd = splitInput[0];
            switch (cmd) {
            case "done":
                if (splitInput.length != 2) {
                    robotPrint("Invalid command! Please use as \"done [task id]\"");
                    break;
                }
                markTaskDone(splitInput[1]);
                break;
            case "list":
                if (splitInput.length != 1) {
                    robotPrint("Invalid command! Please use as \"list\"");
                    break;
                }
                printAllTasks();
                break;
            case "bye":
                if (splitInput.length != 1) {
                    robotPrint("Invalid command! Please use as \"bye\"");
                    break;
                }
                sc.close();
                robotPrint("Bye. Hope to see you again soon!");
                return;
            default:
                addTask(input);
            }
        }
    }

    private static void markTaskDone(String index) {
        // try to parse int
        int i = 0;
        try {
            i = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            robotPrint("Unable to parse task id!");
        }

        if (i < 0 || i > tasks.size()) {
            robotPrint("Invalid task id!");
        }

        Task task = tasks.get(i - 1);
        task.markAsDone();
        robotPrint("Nice! I've marked this task as done:");
        System.out.println("\t " + task);
    }

    private static void addTask(String task) {
        tasks.add(new Task(task));
        robotPrint("added --> " + task);
    }

    private static void printAllTasks() {
        robotPrint("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + ((i + 1) + ". " + tasks.get(i)));
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
