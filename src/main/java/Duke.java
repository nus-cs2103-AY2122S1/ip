import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static final List<String> TASKS = new ArrayList<>();

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
        switch (input) {
            case "bye":
                handleBye();
                break;
            case "list":
                handleList();
                break;
            default:
                handleAdd(input);
        }
    }

    private static void handleAdd(String input) {
        TASKS.add(input);
        hikoPrint("added: " + input + "\n");
    }

    private static void handleList() {
        String output = "";
        for (int i = 1; i <= TASKS.size(); i++) {
            output = output +  i + ". " + TASKS.get(i - 1) + "\n";
        }
        hikoPrint(output);
    }

    private static void handleBye() {
        System.out.println(ANSI_PURPLE + "Bye! Hope to see you again soon!" + ANSI_RESET);
        System.exit(0);
    }
}
