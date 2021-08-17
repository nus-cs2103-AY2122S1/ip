import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
            var parameters = command.split(" ");
            boolean isEnd = false;

            if (command.equals("list")) {
                printBanner(retrieveTaskList());
            } else if (command.equals("bye")) {
                printBanner(BYE_MSG.split("\n"));
                isEnd = true;
            } else if (parameters[0].equals("done") && parameters.length == 2) {
                int i = Integer.parseInt(parameters[1]) - 1;
                var task = tasks.get(i);
                task.toggle();

                printBanner(new String[] {
                    "Nice! I've marked this task as done:",
                    "  " + renderTask(task)
                });
            } else if (parameters[0].equals("todo") && parameters.length > 1) {
                String description = command.replace("todo ", "");
                var task = new Todo(description);
                tasks.add(task);
                printBanner(renderNewTaskList(task));
            } else if (
                parameters[0].equals("deadline")
                && parameters.length > 1
                && Arrays.stream(parameters).anyMatch("/by"::equals)
            ) {
                String[] parts = command.replace("deadline ", "").split(" /by ");
                var task = new Deadline(parts[0], parts[1]);
                tasks.add(task);
                printBanner(renderNewTaskList(task));
            } else if (
                parameters[0].equals("event")
                && parameters.length > 1
                && Arrays.stream(parameters).anyMatch("/at"::equals)
            ) {
                String[] parts = command.replace("event ", "").split(" /at ");
                var task = new Event(parts[0], parts[1]);
                tasks.add(task);
                printBanner(renderNewTaskList(task));
            }

            if (isEnd) {
                break;
            }
        }

        sc.close();
    }

    public static String renderTask(Task task) {
        String taskIcon = task.getTaskIcon();
        String statusIcon = task.getStatusIcon();
        String description = task.getDescription();
        return String.format("[%s][%s] %s", taskIcon, statusIcon, description);
    }

    public static String[] renderNewTaskList(Task task) {
        return new String[] {
            "Got it. I've added this task:",
            "  " + renderTask(task),
            String.format("Now you have %d tasks in the list.", tasks.size())
        };
    }

    public static String[] retrieveTaskList() {
        return Stream.concat(
            Stream.of("Here are the tasks in your list:"),
            IntStream.range(0, tasks.size())
                .mapToObj(i ->
                    String.format("%d. %s", i + 1, renderTask(tasks.get(i)))
                )
        ).toArray(String[]::new);
    }

    public static void printBanner(String[] lines) {
        System.out.println(PREFIX + SEPARATOR);
        for (String line : lines) {
            System.out.println(PREFIX + line);
        }
        System.out.println(PREFIX + SEPARATOR);
    }
}
