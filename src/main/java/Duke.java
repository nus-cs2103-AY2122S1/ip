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
            try {
                boolean isEnd = menu(command);
                if (isEnd) {
                    break;
                }
            } catch (Exception e) {
                printBanner(new String[]{ "☹ OOPS!!! " + e.getMessage() });
            }
        }
        sc.close();
    }

    public static boolean menu(String command) throws Exception {
        var parameters = command.split(" ");
        if (command.equals("list")) {
            printBanner(renderTaskList());
        } else if (command.equals("bye")) {
            printBanner(BYE_MSG.split("\n"));
            return true;
        } else if (parameters[0].equals("done") && parameters.length == 2) {
            int i = Integer.parseInt(parameters[1]) - 1;
            var task = tasks.get(i);
            task.toggle(true);

            printBanner(new String[] {
                "Nice! I've marked this task as done:",
                "  " + renderTask(task)
            });
        } else if (parameters[0].equals("delete") && parameters.length == 2) {
            int i = Integer.parseInt(parameters[1]) - 1;
            var task = tasks.get(i);
            tasks.remove(i);

            printBanner(new String[] {
                "Noted. I've removed this task:",
                "  " + renderTask(task),
                String.format("Now you have %d tasks in the list.", tasks.size()),
            });
        } else if (parameters[0].equals("todo")) {
            if (parameters.length == 1) {
                throw new Exception("The description of a todo cannot be empty.");
            }
            String description = command.replace("todo ", "");
            var task = new Todo(description);
            tasks.add(task);
            printBanner(renderNewTask(task));
        } else if (
            parameters[0].equals("deadline")
            && parameters.length > 1
            && Arrays.stream(parameters).anyMatch("/by"::equals)
        ) {
            String[] parts = command.replace("deadline ", "").split(" /by ");
            var task = new Deadline(parts[0], parts[1]);
            tasks.add(task);
            printBanner(renderNewTask(task));
        } else if (
            parameters[0].equals("event")
            && parameters.length > 1
            && Arrays.stream(parameters).anyMatch("/at"::equals)
        ) {
            String[] parts = command.replace("event ", "").split(" /at ");
            var task = new Event(parts[0], parts[1]);
            tasks.add(task);
            printBanner(renderNewTask(task));
        } else {
            throw new Exception("I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }

    public static String renderTask(Task task) {
        String taskIcon = task.getTaskIcon();
        String statusIcon = task.getStatusIcon();
        String description = task.getDescription();
        return String.format("[%s][%s] %s", taskIcon, statusIcon, description);
    }

    public static String[] renderNewTask(Task task) {
        return new String[] {
            "Got it. I've added this task:",
            "  " + renderTask(task),
            String.format("Now you have %d tasks in the list.", tasks.size())
        };
    }

    public static String[] renderTaskList() {
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
