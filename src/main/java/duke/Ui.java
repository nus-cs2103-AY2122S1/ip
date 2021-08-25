package duke;

import duke.task.Task;

public class Ui {

    public static void printLine() {
        System.out.println("____________________________________________");
    }

    public void printWelcomeMessage() {
        printLine();
        System.out.println("Hello I'm duke\nWhat can I do for you?");
        printLine();
    }

    public void printGoodbyeMessage() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    public static void printDoneMessage(Task task) {
        printLine();
        System.out.println();
        printLine();
    }

    public static void printDeleteMessage(Task task, int taskCount) {
        printLine();
        System.out.println("\tNoted. I've removed this task:\n\t  " + task);
        printTaskCount(taskCount);
        printLine();
    }

    public static void printAddedMessage(Task task, int taskCount) {
        printLine();
        System.out.println("\tGot it. I've added this Task:\n" + "\t  " + task);
        printTaskCount(taskCount);
        printLine();
    }

    private static void printTaskCount(int taskCount) {
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
    }

}
