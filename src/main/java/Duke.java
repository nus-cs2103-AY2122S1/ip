import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static String[] tasks = new String[100];
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
        String welcomeMessage = logo + "Hello! I'm Duke.\n" + "What can I do for you";
        System.out.println(formatDukeResponse(welcomeMessage));
    }

    private static void printExitMessage() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(formatDukeResponse(exitMessage));
    }

    private static void addTask(String taskName) {
        tasks[taskCount] = taskName;
        taskCount++;
    }

    private static void printAddTaskMessage(String taskName) {
        System.out.println(formatDukeResponse("added: " + taskName));
    }

    private static void printTasksMessage() {
        StringBuilder tasksMessage = new StringBuilder();

        for (int i = 0; i < taskCount; i++) {
            int taskNumber = i + 1;
            tasksMessage.append("\n").append(taskNumber).append(". ").append(tasks[i]);
        }

        System.out.println(formatDukeResponse(tasksMessage.toString()));
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                sc.close();
                break;
            }
            if (userInput.equals("list")) {
                printTasksMessage();
                continue;
            }
            addTask(userInput);
            printAddTaskMessage(userInput);
        }

        printExitMessage();
    }
}
