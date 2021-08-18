import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static String formatDukeResponse(String response) {
        return HORIZONTAL_LINE + "\n" + response + "\n" + HORIZONTAL_LINE + "\n";
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = logo + "Hello! I'm Duke.\n" + "What can I do for you?";
        System.out.println(formatDukeResponse(welcomeMessage));
    }

    private static void printExitMessage() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(formatDukeResponse(exitMessage));
    }

    private static void addTask(String description) throws DukeException {
        if (description == null || description.equals("")) {
            throw new DukeException("Oops!!! The description of a todo cannot be empty.");
        }

        Task task = new Todo(description);
        tasks[taskCount] = task;
        taskCount++;
        printAddTaskMessage(task);
    }

    private static void addTask(String descriptionAndTime, String command) throws DukeException {
        String[] splitDescriptionAndTime;
        Task task;

        try {
            if (command.equals("deadline")) {
                splitDescriptionAndTime = descriptionAndTime.split(" /by ");
                task = new Deadline(splitDescriptionAndTime[0].trim(), splitDescriptionAndTime[1].trim());
            } else if (command.equals("event")) {
                splitDescriptionAndTime = descriptionAndTime.split(" /at ");
                task = new Event(splitDescriptionAndTime[0].trim(), splitDescriptionAndTime[1].trim());
            } else {
                printInvalidCommandMessage();
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Oops!!! Deadlines or events should contain a description, followed by " +
                    "a /by or /at respectively, followed by a date or a time.");
        }

        tasks[taskCount] = task;
        taskCount++;
        printAddTaskMessage(task);
    }

    private static void markTask(String taskNumberString) throws DukeException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberString);
        } catch (NumberFormatException e) {
            throw new DukeException("Oops!!! The done command should be followed by an integer.");
        }

        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new DukeException("Oops!!! The task number provided is not valid.");
        }

        Task task = tasks[taskNumber - 1];
        task.markAsDone();
        printMarkTaskDoneMessage(task);
    }

    private static void printAddTaskMessage(Task task) {
        System.out.println(formatDukeResponse("Got it. I've added this task:\n" + task
                + "\nNow you have " + taskCount
                + (taskCount == 1 ? " task " : " tasks ") + "in the list."));
    }

    private static void printTasksMessage() {
        StringBuilder tasksMessage = new StringBuilder("Here are the tasks in your list:");

        for (int i = 0; i < taskCount; i++) {
            int taskNumber = i + 1;
            tasksMessage.append("\n").append(taskNumber).append(".").append(tasks[i]);
        }

        System.out.println(formatDukeResponse(tasksMessage.toString()));
    }

    private static void printMarkTaskDoneMessage(Task task) {
        System.out.println(formatDukeResponse("Nice! I've marked this task as done:\n" + task));
    }

    private static void printInvalidCommandMessage() {
        System.out.println(formatDukeResponse("Oops!!! I'm sorry, but I don't know what that means."));
    }

    private static void printDukeExceptionMessage(DukeException e) {
        System.out.println(formatDukeResponse(e.getMessage()));
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String[] userInput = sc.nextLine().trim().split(" ", 2);
            String command = userInput[0];
            String action = userInput.length == 2 ? userInput[1].trim() : "";

            if (command.equals("bye")) {
                sc.close();
                break;
            }

            try {
                switch (command) {
                case "list":
                    printTasksMessage();
                    break;
                case "done":
                    markTask(action);
                    break;
                case "todo":
                    addTask(action);
                    break;
                case "deadline":
                case "event":
                    addTask(action, command);
                    break;
                default:
                    printInvalidCommandMessage();
                    break;
                }
            } catch (DukeException e) {
                printDukeExceptionMessage(e);
            }
        }

        printExitMessage();
    }
}
