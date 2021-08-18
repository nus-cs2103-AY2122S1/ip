import exception.DukeExtractCommandException;
import exception.DukeTaskNumberOutOfBoundsException;
import exception.DukeUnknownException;
import task.*;
import utils.CommandUtils;

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

    private static Operation getOperation(String command) {
        try {
            return CommandUtils.extractOperation(command);
        } catch (DukeExtractCommandException | DukeUnknownException e) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println(e.getMessage());
            System.out.println(HORIZONTAL_LINE);
        }
        return null;
    }

    private static int getTaskNumber(String command) {
        int number = 0;
        try {
            number = CommandUtils.extractTaskNumber(command);
        } catch (DukeExtractCommandException | NumberFormatException | DukeTaskNumberOutOfBoundsException e) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println(e.getMessage());
            System.out.println(HORIZONTAL_LINE);
        }
        return number;
    }

    private static String getTaskDescription(String command) {
        String description = "";
        try {
            description = CommandUtils.extractTaskDescription(command);
        } catch (DukeExtractCommandException e) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println(e.getMessage());
            System.out.println(HORIZONTAL_LINE);
        }
        return description;
    }

    private static void addTask(TaskManager taskManager, Operation operation, String description) {
        System.out.println(HORIZONTAL_LINE);
        switch (operation) {
            case TODO:
                Todo todo = new Todo(description);
                taskManager.addTask(todo);
                System.out.println(INDENTATION + "Got it. I've added this task:");
                System.out.println(INDENTATION + "  " + todo.toString());
                System.out.println(INDENTATION + "Now you have " + taskManager.size() + " " +
                        (taskManager.size() <= 1 ? "task" : "tasks") + " in the list.");
                break;
            case DEADLINE:
                try {
                    String[] taskDetails = CommandUtils.extractTaskDetails(description, " /by ");
                    String taskName = taskDetails[0];
                    String byTime = taskDetails[1];
                    Deadline deadline = new Deadline(taskName, byTime);
                    taskManager.addTask(deadline);
                    System.out.println(INDENTATION + "Got it. I've added this task:");
                    System.out.println(INDENTATION + "  " + deadline.toString());
                    System.out.println(INDENTATION + "Now you have " + taskManager.size() + " " +
                            (taskManager.size() <= 1 ? "task" : "tasks") + " in the list.");
                } catch (DukeExtractCommandException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    String[] taskDetails = CommandUtils.extractTaskDetails(description, " /at ");
                    String taskName = taskDetails[0];
                    String atTime = taskDetails[1];
                    Event event = new Event(taskName, atTime);
                    taskManager.addTask(event);
                    System.out.println(INDENTATION + "Got it. I've added this task:");
                    System.out.println(INDENTATION + "  " + event.toString());
                    System.out.println(INDENTATION + "Now you have " + taskManager.size() + " " +
                            (taskManager.size() <= 1 ? "task" : "tasks") + " in the list.");
                } catch (DukeExtractCommandException e) {
                    System.out.println(e.getMessage());
                }
                break;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void completeTask(TaskManager taskManager, int number) {
        try {
            taskManager.completeTask(number);
            System.out.println(HORIZONTAL_LINE);
            System.out.println(INDENTATION + "Nice! I've marked this task as done:");
            System.out.println(INDENTATION + "  " + taskManager.findTaskByNumber(number).toString());
            System.out.println(HORIZONTAL_LINE);
        } catch (DukeTaskNumberOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteTask(TaskManager taskManager, int number) {
        try {
            Task task = taskManager.deleteTask(number);
            System.out.println(HORIZONTAL_LINE);
            System.out.println(INDENTATION + "Noted. I've removed this task:");
            System.out.println(INDENTATION + "  " + task.toString());
            System.out.println(INDENTATION + "Now you have " + taskManager.size() + " " +
                    (taskManager.size() <= 1 ? "task" : "tasks") + " in the list.");
            System.out.println(HORIZONTAL_LINE);
        } catch (DukeException de) {
            System.out.println(de.getMessage());
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
        Operation operation = getOperation(command);
        while (true) {
            if (operation == Operation.BYE) {
                bye();
                break;
            } else if (operation == Operation.LIST) {
                listTasks(taskManager);
            } else if (operation == Operation.DONE) {
                int number = getTaskNumber(command);
                if (number > 0) {
                    completeTask(taskManager, number);
                }
            } else if (operation == Operation.DELETE) {
                int number = getTaskNumber(command);
                if (number > 0) {
                    deleteTask(taskManager, number);
                }
            } else if (operation == Operation.TODO ||
                    operation == Operation.DEADLINE ||
                    operation == Operation.EVENT) {
                String description = getTaskDescription(command);
                if (!description.equals("")) {
                    addTask(taskManager, operation, description);
                }
            }
            command = scanner.nextLine();
            operation = getOperation(command);
        }
    }
}
