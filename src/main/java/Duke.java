import java.util.Scanner;

/**
 * The project aims to build a product named Duke,
 * a Personal Assistant Chatbot that helps a person
 * to keep track of various things.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

/**
 * This is the Main class that will contain the main method
 * to be executed at run-time.
 */

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String BYE = "bye";

    private static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Hello! I'm Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void echo(String command) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + command);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addTask(TaskManager taskManager, String taskName) {
        System.out.println(HORIZONTAL_LINE);
        Task task = new Task(taskName);
        taskManager.addTask(task);
        System.out.println(INDENTATION + "added: " + task.getName());
        System.out.println(HORIZONTAL_LINE);
    }

    private static void completeTask(TaskManager taskManager, int number) {
        if (taskManager.completeTask(number)) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println(INDENTATION + "Nice! I've marked this task as done:");
            System.out.println(INDENTATION + "  " + taskManager.findTaskByNumber(number).toString());
            System.out.println(HORIZONTAL_LINE);
        } else {
            System.out.println("Complete task error.");
        }
    }

    private static void listTasks(TaskManager taskManager) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        taskManager.printTasks();
        System.out.println(HORIZONTAL_LINE);
    }

    private static void bye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * This is Main method.
     *
     * @param args an array of command-line arguments for the application
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        greet();
        String command = scanner.nextLine();
        String operation = command.split(" ")[0];
        while (true) {
            if (operation.equals(BYE)) {
                bye();
                break;
            } else if (operation.equals(LIST)) {
                listTasks(taskManager);
            } else if (operation.contains(DONE)) {
                try {
                    int number = Integer.parseInt(command.split(" ")[1]);
                    completeTask(taskManager, number);
                } catch (Exception e) {
                    System.out.println("Input error: " + e.getMessage() + ", please try again.");
                }
            } else {
                addTask(taskManager, command);
            }
            command = scanner.nextLine();
            operation = command.split(" ")[0];
        }
    }
}
