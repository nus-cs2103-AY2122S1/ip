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
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void greetUser() {
        System.out.println(doubleDivider + greeting + doubleDivider);
        System.out.println("Please enter the tasks to be added to the list.\n" +
                "(Enter 'list' to view the list, or 'bye' to exit.)\n" + divider);
    }

    public static void byeUser() {
        System.out.println(divider + "Bye. Hope to see you again soon!\n" + divider);
    }

    public static void printList() {
        System.out.println(divider + "Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            System.out.println(i + ". [" + task.getStatusIcon() + "] " +  task.getDescription());
        }
        System.out.print(divider);
    }

    public static void markTaskAsDone(String taskNum) {
        int taskIdx = Integer.valueOf(taskNum) - 1;
        Task task = taskList.get(taskIdx);
        task.markAsDone();
        System.out.print(divider + "Great! I've marked this task as done:\n["
                + task.getStatusIcon() + "] " +  task.getDescription() + "\n" + divider);
    }

    public static void getInputs() {
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                byeUser();
                break;
            } else if (input.equals("list")) {
                printList();
            } else {
                String[] inputs = input.split(" ");
                if (inputs[0].equals("done") && inputs.length > 1) {
                    markTaskAsDone(inputs[1]);
                } else {
                    taskList.add(new Task(input));
                    System.out.println(divider + "added: " + input + "\n" + divider);
                }
            }
        }
    }

    public static void main(String[] args) {
        greetUser();
        getInputs();
    }
}
