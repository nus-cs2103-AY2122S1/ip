import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static final List<Task> TASKS = new ArrayList<>();

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        while (true) {
            handleInput(sc.nextLine());
        }
    }

    private static void greet() {
        String logo =
                " _   _ _ _\n"+
                "| | | (_) | _____\n"+
                "| |_| | | |/ / _ \\\n"+
                "|  _  | |   < (_) |\n"+
                "|_| |_|_|_|\\_\\___/\n";

        hikoPrint("Hello from\n" + logo + "What can I do for you?\n");
    }

    private static void hikoPrint(String str) {
        System.out.print(ANSI_PURPLE + str + ANSI_RESET + "\n> ");
    }

    private static void handleInput(String input) {
        Command command = new Command(input);
        switch (command.getAction()) {
            case "bye":
                handleBye();
                break;
            case "list":
                handleList();
                break;
            case "done":
                handleMarkDone(command.getArgument());
                break;
            default:
                handleAdd(input);
        }
    }

    private static void handleMarkDone(String arg) {
        int idx = Integer.parseInt(arg);
        Task task = TASKS.get(idx - 1);
        task.setDone();
        hikoPrint("Nice! I've marked this task as done:\n" + task +"\n");
    }

    private static void handleAdd(String input) {
        Task task = new Task(input);
        TASKS.add(task);
        hikoPrint("added: " + input + "\n");
    }

    private static void handleList() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 1; i <= TASKS.size(); i++) {
            Task task = TASKS.get(i - 1);
            output += i + ". " + task + "\n";
        }
        hikoPrint(output);
    }

    private static void handleBye() {
        System.out.println(ANSI_PURPLE + "Bye! Hope to see you again soon!" + ANSI_RESET);
        System.exit(0);
    }
}
