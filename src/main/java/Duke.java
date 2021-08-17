import exception.TaskManagerException;
import task.Task;

import java.util.Scanner;

public class Duke {

    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static TaskManager TASK_MANAGER = new TaskManager();

    public static void main(String[] args) {
        greet();
        run();
        exit();
    }

    public static void greet() {
        Echoer.print("Hello from\n" + LOGO);
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
                Echoer.list(TASK_MANAGER.listTasks());

            } else if (userInput.startsWith("done")) {
                String taskNumberString = userInput.substring(4).trim();
                Task completedTask = TASK_MANAGER.markTaskAsDone(taskNumberString);
                Echoer.info("Nice! I've marked this task as done:\n\t  " + completedTask);

            } else if (userInput.startsWith("todo")) {
                echoTaskCreation(TASK_MANAGER.addToDoTask(userInput.substring(4)));

            } else if (userInput.startsWith("event")) {
                echoTaskCreation(TASK_MANAGER.addEventTask(userInput.substring(5)));

            } else if (userInput.startsWith("deadline")) {
                echoTaskCreation(TASK_MANAGER.addDeadlineTask(userInput.substring(8)));

            } else {
                Echoer.error("Please ensure instruction follows specified format.");
            }

        } catch (TaskManagerException exception) {
            Echoer.error(exception.getLocalizedMessage());
        }
    }

    public static void echoTaskCreation(Task task) {
        Echoer.info("Got it. I've added this task:\n\t  " + task + "\n\tNow you have " +
                TASK_MANAGER.getTaskListSize() + " tasks in the list.");
    }
}
