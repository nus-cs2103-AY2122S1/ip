import java.util.Scanner;

public class Duke {
    private final static String GREETING_MSG = "Hello! I'm Duke\nWhat can I do for you?";
    private final static String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    private final static String ADD_TASK_MSG_TEMPLATE = "Got it. I've added this task:\n"
                                                        + "    %s\n"
                                                        + "Now you have %d tasks in the list.\n";
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

    private static void runCommand(String cmd) throws DukeException {
        if (cmd.matches("^todo[ \\t]*$")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (cmd.matches("^deadline[ \\t]*$")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (cmd.matches("^event[ \\t]*$")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (cmd.matches("^todo[ \\t]+.+$")) {
            addTodo(cmd.split("[ \\t]+", 2)[1]);
        } else if (cmd.matches("^deadline[ \\t]+.+[ \\t]+/by[ \\t]+.+$")) {
            String[] bySplit = cmd.split("[ \\t]+/by[ \\t]+", 2);
            addDeadline(bySplit[0].split("^deadline[ \\t]+")[1], bySplit[1]);
        } else if (cmd.matches("^event[ \\t]+.+[ \\t]+/at[ \\t]+.+$")) {
            String[] atSplit = cmd.split("[ \\t]+/at[ \\t]+", 2);
            addEvent(atSplit[0].split("^event[ \\t]+")[1], atSplit[1]);
        } else if (cmd.matches("^list[ \\t]*$")) {
            listTasks();
        } else if (cmd.matches("^done[ \\t]+[0-9]+")) {
            tasks[Integer.parseInt(cmd.split("^done[ \\t]+")[1]) - 1].markAsDone();
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


    public static void main(String[] args) {
        greet();
        for (String cmd = SCANNER.nextLine(); !cmd.equals("bye"); cmd = SCANNER.nextLine()) {
            try {
                runCommand(cmd);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        exit();
    }
}
