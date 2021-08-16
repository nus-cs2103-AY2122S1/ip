import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {
    static String SEPARATOR = "____________________________________________________________";
    static String PREFIX = "\t";
    static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String WELCOME_MSG = "Hello from\n" + LOGO + "What can I do for you?";
    static String BYE_MSG = "Bye. Hope to see you again soon!";

    static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        printBanner(WELCOME_MSG.split("\n"));

        var sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            var command = sc.nextLine();
            boolean isEnd = false;

            if (command.equals("list")) {
                printBanner(retrieveTaskList());
            } else if (command.equals("bye")) {
                printBanner(BYE_MSG.split("\n"));
                isEnd = true;
            } else if (command.startsWith("done")) {
                String[] parameters = command.split(" ");
                if (parameters.length > 2) {
                    throw new IllegalArgumentException("done command has too many parameters");
                }
                int i = Integer.parseInt(parameters[1]) - 1;

                var task = tasks.get(i);
                task.toggle();

                printBanner(new String[] {
                    "Nice! I've marked this task as done:",
                    "  " + renderTask(task)
                });
            } else {
                var task = new Task(command);
                tasks.add(task);
                printBanner(new String[] {"added: " + task.getDescription()});
            }

            if (isEnd) {
                break;
            }
        }

        sc.close();
    }

    public static String renderTask(Task task) {
        String statusIcon = task.getStatusIcon();
        String description = task.getDescription();
        return String.format("[%s] %s", statusIcon, description);
    }

    public static String[] retrieveTaskList() {
        return IntStream.range(0, tasks.size())
            .mapToObj(i ->
                String.format("%d. %s", i + 1, renderTask(tasks.get(i)))
            )
            .toArray(String[]::new);
    }

    public static void printBanner(String[] lines) {
        System.out.println(PREFIX + SEPARATOR);
        for (String line : lines) {
            System.out.println(PREFIX + line);
        }
        System.out.println(PREFIX + SEPARATOR);
    }
}
