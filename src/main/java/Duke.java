import java.util.Scanner;

public class Duke {
    private final static String GREETING_MSG = "Hello! I'm Duke\nWhat can I do for you?";
    private final static String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    private final static String ADD_TASK_MSG_TEMPLATE = "Got it. I've added this task:\n\t%s"
                                                        + "\nNow you have %d tasks in the list.\n";
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Task[] tasks = new Task[100];
    private static int numTasks = 0;

    private static void greet() {
        System.out.println(GREETING_MSG);
    }

    private static void exit() {
        System.out.println(GOODBYE_MSG);
    }


    private static void addTodo(String description) {
        Task taskToAdd = new Todo(description);
        tasks[numTasks++] = taskToAdd;
        System.out.printf(ADD_TASK_MSG_TEMPLATE, taskToAdd, numTasks);
    }

    private static void addDeadline(String description, String by) {
        Task taskToAdd = new Deadline(description, by);
        tasks[numTasks++] = taskToAdd;
        System.out.printf(ADD_TASK_MSG_TEMPLATE, taskToAdd, numTasks);
    }

    private static void addEvent(String description, String at) {
        Task taskToAdd = new Event(description, at);
        tasks[numTasks++] = taskToAdd;
        System.out.printf(ADD_TASK_MSG_TEMPLATE, taskToAdd, numTasks);
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; tasks[i] != null; i++) {
            System.out.printf("%d.%s%n", i + 1, tasks[i]);
        }
    }


    public static void main(String[] args) {
        greet();
        for (String cmd = SCANNER.nextLine(); !cmd.equals("bye"); cmd = SCANNER.nextLine()) {
            if (cmd.matches("todo .+")) {
                addTodo(cmd.substring("todo ".length()));
            } else if (cmd.matches("deadline .+ /by .+")) {
                String[] bySplit = cmd.split(" /by ", 2);
                addDeadline(bySplit[0].substring("deadline ".length()), bySplit[1]);
            } else if (cmd.matches("event .+ /at .+")) {
                String[] atSplit = cmd.split(" /at ", 2);
                addEvent(atSplit[0].substring("event ".length()), atSplit[1]);
            } else if (cmd.equals("list")) {
                listTasks();
            } else if (cmd.matches("done [0-9]+")) {
                tasks[Integer.parseInt(cmd.substring("done ".length())) - 1].markAsDone();
            }
        }
        exit();
    }
}
