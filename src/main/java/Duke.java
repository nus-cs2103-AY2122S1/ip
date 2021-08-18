import exception.TaskManagerException;
import task.Task;

import java.util.Scanner;

public class Duke {

    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String EXIT_KEY = "bye";

    private final DukeChatBot dukeChatBot = new DukeChatBot();

    private final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        dukeChatBot.print("Hello from\n" + LOGO);
        dukeChatBot.info("Hello! I'm Duke.\n\tWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim();
        while (!userInput.equals(EXIT_KEY)) {
            makeDecision(userInput);
            userInput = scanner.nextLine().trim();
        }
        scanner.close();

        dukeChatBot.info("Bye. Hope to see you again soon!");
    }

    public void makeDecision(String userInput) {
        try {
            if (userInput.equals("list")) {
                dukeChatBot.list(taskManager.listTasks());

            } else if (userInput.startsWith("done")) {
                String taskNumberString = userInput.substring(4).trim();
                Task completedTask = taskManager.markTaskAsDone(taskNumberString);
                dukeChatBot.info("Nice! I've marked this task as done:\n\t  " + completedTask);

            } else if (userInput.startsWith("delete")) {
                String taskNumberString = userInput.substring(6).trim();
                Task deletedTask = taskManager.deleteTask(taskNumberString);
                dukeChatBot.info("Noted. I've removed this task:\n\t  " + deletedTask +
                        "\n\tNow you have " + taskManager.getTaskListSize() +
                        " tasks in the list.");

            } else if (userInput.startsWith("todo")) {
                echoTaskCreation(taskManager.addToDoTask(userInput.substring(4)));

            } else if (userInput.startsWith("event")) {
                echoTaskCreation(taskManager.addEventTask(userInput.substring(5)));

            } else if (userInput.startsWith("deadline")) {
                echoTaskCreation(taskManager.addDeadlineTask(userInput.substring(8)));

            } else {
                dukeChatBot.error("Please ensure instruction follows specified format.");
            }

        } catch (TaskManagerException exception) {
            dukeChatBot.error(exception.getLocalizedMessage());
        }
    }

    public void echoTaskCreation(Task task) {
        dukeChatBot.info("Got it. I've added this task:\n\t  " + task + "\n\tNow you have " +
                taskManager.getTaskListSize() + " tasks in the list.");
    }
}
