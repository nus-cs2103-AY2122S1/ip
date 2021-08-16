import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static List<Task> tasks = new ArrayList<>();

    private static void printReply(String string) {
        System.out.println(new Reply(string));
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printReply("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void add(String string) {
        tasks.add(new Task(string));
        printReply("added: " + string);
    }

    private static void list() {
        StringBuilder tasksBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            String counter = String.valueOf(i + 1);
            tasksBuilder.append(counter).append(". ").append(tasks.get(i).toString());
            if (i < tasks.size() - 1) {
                // Append newline for all tasks before last task
                tasksBuilder.append("\n");
            }
        }
        printReply(tasksBuilder.toString());
    }

    public static void main(String[] args) {
        greet();
        String readIn;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            readIn = scanner.nextLine();
            if (readIn.equals(EXIT_COMMAND)) {
                break;
            } else if (readIn.equals(LIST_COMMAND)) {
                list();
            } else {
                add(readIn);
            }
        }
        printReply("Bye. Hope to see you again soon!");
    }
}
