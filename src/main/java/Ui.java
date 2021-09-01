import java.util.Scanner;

public class Ui {
    // Constant Strings
    protected static final String HORIZONTAL_LINE = "____________________________________________________________";
    protected static final String LEFT_INDENT = "    ";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    protected String readCommand() {
        return scanner.nextLine();
    }

    protected static void printWithIndent(String s) {
        System.out.println(LEFT_INDENT + s);
    }

    protected static void printDividerLine() {
        System.out.println(LEFT_INDENT + HORIZONTAL_LINE);
    }

    protected static void printWelcomeMessage() {
        // print logo
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // print welcome words
        printDividerLine();
        printWithIndent("Hello! I'm Duke");
        printWithIndent("What can I do for you?");
        printDividerLine();
    }

    protected static void printGoodbyeMessage() {
        printWithIndent("Bye. Hope to see you again soon!");
    }

    protected static void printList(String[] listStringArr) {
        for (int i = 0; i < listStringArr.length; i++) {
            printWithIndent((i+1) + "." + listStringArr[i]);
        }
    }

    protected static void printTaskCount(int listSize) {
        printWithIndent(getTaskCountString(listSize));
    }

    protected static <T extends Task> void printNewTask(T task) {
        printWithIndent("Got it. I've added this task:");
        printWithIndent("  " + task);
    }

    protected static void printErrorMessage(DukeException e, String userInput) {
        switch (e.type) {
            case INDEX_OUT_OF_BOUND:
            case INVALID_COMMAND:
            case INVALID_OPERAND:
            case MISSING_OPERAND:
                printWithIndent(userInput + ": " + e.getMessage());
                break;
            case DDL_MISSING_KEYWORD:
                printWithIndent(e.getMessage() + ". Correct format is:");
                printWithIndent("deadline {task description} /by {due time}");
                printWithIndent("Example: deadline return book /by Sunday");
                break;
            case EVENT_MISSING_KEYWORD:
                printWithIndent(e.getMessage() + ". Correct format is:");
                printWithIndent("event {task description} /at {time period}");
                printWithIndent("Example: event project meeting /at Mon 2-4pm");
                break;
        }
    }

    protected static void printErrorMessage(String s) {
        printWithIndent(s);
    }

    protected static String getTaskCountString(int listSize) {
        return "Now you have " + listSize + " tasks in the list.";
    }
}
