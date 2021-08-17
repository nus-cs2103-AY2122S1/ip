import java.util.Scanner;

public class Duke {
    private final static String GREETING_MSG = "Hello! I'm Duke\nWhat can I do for you?";
    private final static String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Task[] tasks = new Task[100];
    private static int numTasks = 0;

    private static void greet() {
        System.out.println(GREETING_MSG);
    }

    private static void exit() {
        System.out.println(GOODBYE_MSG);
    }

    private static void addTask(String description) {
        final Task taskToAdd = new Task(description);
        tasks[numTasks++] = taskToAdd;
        System.out.println(String.format("added: %s", taskToAdd.getDescription()));
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; tasks[i] != null; i++) {
            System.out.println(String.format("%d.%s", i + 1, tasks[i]));
        }
    }

    private static boolean isMarkTask(String cmd) {
        String[] cmd_split = cmd.split(" ");
        return cmd_split.length == 2 && cmd_split[0].equals("done") && cmd_split[1].matches("[0-9]+");
    }


    public static void main(String[] args) {
        greet();
        for (String cmd = SCANNER.nextLine(); !cmd.equals("bye"); cmd = SCANNER.nextLine()) {
            String[] cmd_split = cmd.split(" ");
            if (isMarkTask(cmd)) {
                tasks[Integer.parseInt(cmd_split[1]) - 1].markAsDone();
            } else if (cmd.equals("list")) {
                listTasks();
            } else {
                addTask(cmd);
            }
        }
        exit();
    }
}
