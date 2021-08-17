import exception.TaskManagerException;

import java.util.Scanner;

public class Duke {

    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        printLogo();
        greet();
        run();
        exit();
    }

    public static void printLogo() {
        Echoer.print("Hello from\n" + LOGO);
    }

    public static void greet() {
        Echoer.info("Hello! I'm Duke.\n\tWhat can I do for you?");
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim();
        while (!userInput.equals("bye")) {
            makeDecision(userInput);
            userInput = scanner.nextLine().trim();
        }
        scanner.close();
    }

    public static void exit() {
        Echoer.info("Bye. Hope to see you again soon!");
    }

    public static void makeDecision(String userInput) {
        try {
            if (userInput.equals("list")) {
                TaskManager.listTasks();

            } else if (userInput.startsWith("done")) {
                String taskNumberString = userInput.substring(4).trim();
                int taskNumber = Integer.parseInt(taskNumberString);
                TaskManager.markTaskAsDone(taskNumber);

            } else if (userInput.startsWith("todo")) {
                TaskManager.addToDoTask(userInput.substring(4));

            } else if (userInput.startsWith("event")) {
                TaskManager.addEventTask(userInput.substring(5));

            } else if (userInput.startsWith("deadline")) {
                TaskManager.addDeadlineTask(userInput.substring(8));

            } else {
                Echoer.error("Please ensure instruction follows specified format.");
            }

        } catch (TaskManagerException exception) {
            Echoer.error(exception.getLocalizedMessage());
        }
    }
}
