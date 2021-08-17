import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String greeting = " __          __  _                            _______    \n" +
            " \\ \\        / / | |                          |__   __|   \n" +
            "  \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___     | | ___  \n" +
            "   \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\    | |/ _ \\ \n" +
            "    \\  /\\  /  __/ | (_| (_) | | | | | |  __/    | | (_) |\n" +
            "     \\/  \\/ \\___|_|\\___\\___/|_|_|_| |_|\\___|    |_|\\___/ \n" +
            "               | \\ | |/ __ \\|  __ \\|  \\/  |              \n" +
            "               |  \\| | |  | | |__) | \\  / |              \n" +
            "               | . ` | |  | |  _  /| |\\/| |              \n" +
            "               | |\\  | |__| | | \\ \\| |  | |              \n" +
            "               |_| \\_|\\____/|_|  \\_\\_|  |_|\n";
    private static final String divider = "──────────────────────────────────────────────────────────\n";
    private static final String doubleDivider = "══════════════════════════════════════════════════════════\n";

    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<String> taskList = new ArrayList<>();

    public static void greetUser() {
        System.out.println(doubleDivider + greeting + doubleDivider);
        System.out.println("Please enter the tasks to be added to the list.\n" +
                "(Enter 'list' to view the list, or 'bye' to exit.)\n" + divider);
    }

    public static void byeUser() {
        System.out.println(divider + "Bye. Hope to see you again soon!\n" + divider);
    }

    public static void printList() {
        System.out.print(divider);
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i - 1));
        }
        System.out.print(divider);
    }

    public static void main(String[] args) {

        greetUser();

        while (true) {
            String task = scanner.nextLine();
            if (task.equals("bye") || task.equals("Bye")) {
                byeUser();
                break;
            } else if (task.equals("list") || task.equals("List")) {
                printList();
            } else {
                taskList.add(task);
                System.out.println(divider + "added: " + task + "\n" + divider);
            }
        }
    }
}
