package duke;

import duke.task.*;

import java.util.ArrayList;
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

    protected static void printList(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            printWithIndent((i+1) + "." + taskList.get(i).toString());
        }
    }

    protected static void printTaskCount(int listSize) {
        printWithIndent(getTaskCountString(listSize));
    }

    protected static void printNewTask(String taskStr) {
        printWithIndent("Got it. I've added this task:");
        printWithIndent("  " + taskStr);
    }

    protected static void printRemoveTask(String taskStr) {
        printWithIndent("Noted. I've removed this task: ");
        printWithIndent("  " + taskStr);
    }

    protected static void printMarkDone(String taskStr) {
        Ui.printWithIndent("Nice! I've marked this task as done: ");
        Ui.printWithIndent("  " + taskStr);
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
            printWithIndent("deadline {description} /by {due time}");
            printWithIndent("Example: deadline return book /by Sunday");
            break;
        case EVENT_MISSING_KEYWORD:
            printWithIndent(e.getMessage() + ". Correct format is:");
            printWithIndent("event {description} /at {time period}");
            printWithIndent("Example: event project meeting /at Mon 2-4pm");
            break;
        case PIPE_SYMBOL:
        case FAIL_TO_READ:
        case FAIL_TO_WRITE:
            printWithIndent(e.getMessage());
            break;
        }
    }

    protected static void printErrorMessage(DukeException e) {
        printErrorMessage(e, "");
    }

    protected static String getTaskCountString(int listSize) {
        return "Now you have " + listSize + " tasks in the list.";
    }
}
