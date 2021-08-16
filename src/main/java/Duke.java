import java.util.Scanner;

public class Duke {
    private Task[] tasks = new Task[100];
    private int count = 0;

    /**
     * Print with 4 spaces infront of param str.
     *
     * @param str A String to be printed
     */
    public static void printWithTabIndent(String str) {
        System.out.println("    " + str);
    }

    /**
     * Print horizontal line.
     */
    public static void printLine() {
        printWithTabIndent("------------------------------------------");
    }

    /**
     * Pretty print the message with the horizontal lines and the param message.
     *
     * @param message A String to be printed
     */
    public static void printMessage(String message) {
        printLine();
        printWithTabIndent(message);
        printLine();
    }

    /**
     * Pretty print the tasks list with the horizontal lines.
     */
    public void printTasks() {
        if (count == 0) {
            printMessage("Nothing in the list!");
        } else {
            printLine();
            for (int i = 0; i < count; i++) {
                printWithTabIndent(String.format("%d. %s", i + 1, tasks[i].toString()));
            }
            printLine();
        }
    }

    public void markTaskDone(String message) {
        if (message.equals("done")) {
            printMessage("Please enter the task number.");
        } else if (message.matches("^done \\d+$")) {
            String taskNo = message.split(" ")[1];
            try {
                int taskNoInt = Integer.parseInt(taskNo) - 1;
                if (count == 0) {
                    printMessage("Nothing in the list");
                } else if (taskNoInt >= count || taskNoInt < 0) {
                    printMessage(String.format("Enter a valid number between 1 - %d", count));
                } else if (tasks[taskNoInt].isDone()) {
                    printMessage(
                            String.format("Task %s is already done!\n    %s",
                                    taskNo,
                                    tasks[taskNoInt].toString()
                            )
                    );
                } else {
                    tasks[taskNoInt].markAsDone();
                    printMessage(
                            String.format("Nice! I've marked this task as done:\n       %s",
                                    tasks[taskNoInt].toString()
                            )
                    );
                }
            } catch (NumberFormatException e) {
                printMessage("Not a number!");
            }
        } else if (message.matches("^done [a-zA-Z]+.*$")) {
            printMessage("Invalid Input");
        } else {
            addTask(message);
        }
    }

    public void addTask(String message) {
        printMessage("added: " + message);
        tasks[count] = new Task(message);
        count++;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        printMessage("Hello! I'm Duke\n    What can I do for you?");
        String message = scanner.nextLine();
        while (!message.equals("bye")) {
            message = message.trim();
            if (message.equals("list")) {
                duke.printTasks();
            } else if (message.matches("^done.*")) {
                duke.markTaskDone(message);
            } else if (!message.equals("")) {
                duke.addTask(message);
            }
            message = scanner.nextLine();
        }
        printMessage("Bye. Hope to see you again soon!");
    }
}
