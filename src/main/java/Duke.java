import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ENDING_COMMAND = "bye";

    private static List<String> taskList = new ArrayList<>();

    private static void say(String message) {
        System.out.println(String.format("Iris: %s", message));
    }

    private static void say(String message, boolean isFirst) {
        String name = isFirst ? "Iris:": "     ";
        System.out.println(String.format("%s %s", name, message));
    }

    private static String prompt() {
        System.out.print("me: ");
        return scanner.nextLine();
    }

    private static void echo(String message) {
        say(message);
    }

    private static void add(String item) {
        say(String.format("added: %s", item));
        taskList.add(item);
    }

    private static void listTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            say(String.format("%d. %s", i + 1, taskList.get(i)), i == 0);
        }
    }

    public static void main(String[] args) {
        say("Hello! I'm Iris. What can I do for you?");
        String command = prompt();
        while (!command.equals(ENDING_COMMAND)) {
            switch(command) {
                case "list":
                    listTasks();
                    break;
                default:
                    add(command);
            }
            command = prompt();
        }

        say("Bye. Hope to see you again soon!");
    }
}
