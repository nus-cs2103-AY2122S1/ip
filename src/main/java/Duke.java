import task.*;

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
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
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

    private static void addTask(TaskManager taskManager, String operation, String content) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        switch (operation) {
            case TODO:
                Todo todo = new Todo(content);
                taskManager.addTask(todo);
                System.out.println(INDENTATION + "  " + todo.toString());
                break;
            case DEADLINE: {
                String taskName = content.split(" /by ", 2)[0];
                String byTime = content.split(" /by ", 2)[1];
                Deadline deadline = new Deadline(taskName, byTime);
                taskManager.addTask(deadline);
                System.out.println(INDENTATION + "  " + deadline.toString());
                break;
            }
            case EVENT: {
                String taskName = content.split(" /at ", 2)[0];
                String atTime = content.split(" /at ", 2)[1];
                Event event = new Event(taskName, atTime);
                taskManager.addTask(event);
                System.out.println(INDENTATION + "  " + event.toString());
                break;
            }
        }
        System.out.println(INDENTATION + "Now you have " + taskManager.size() + " " +
                (taskManager.size() <= 1 ? "task" : "tasks") + " in the list.");
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
                    int number = Integer.parseInt(command.split(" ", 2)[1]);
                    completeTask(taskManager, number);
                } catch (Exception e) {
                    System.out.println("Input error: " + e.getMessage() + ", please try again.");
                }
            } else {
                try {
                    String content = command.split(" ", 2)[1];
                    addTask(taskManager, operation, content);
                } catch (Exception e) {
                    System.out.println("Input error: " + e.getMessage() + ", please try again.");
                }
            }
            command = scanner.nextLine();
            operation = command.split(" ")[0];
        }
    }
}
