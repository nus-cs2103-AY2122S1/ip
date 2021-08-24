package duke.functionality;

import duke.tasks.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.List;

public class Ui {
    private static final String BORDER = "____________________________________________________________";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String PLACEHOLDER = "%text%";
    private static final String DONE_TASK = "Nice! I've marked this task as done:";
    private static final String MAX_TASK = "Sorry! You have max number of tasks stored already.";
    private static final String ADDED_TASK = "Got it. I've added this task:";
    private static final String REMAINING_TASK_NUM = String.format("Now you have %s tasks in the list.", PLACEHOLDER);
    private static final String DELETED_TASK = "Noted. I've removed this task:";
    private static final String MISSING_TASK = "There is no task at the specified index.";
    private static final String NO_MATCHING_TASKS = "There are no matching tasks found.";
    private static final String UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";


    private final Scanner sc;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    private Ui(InputStream in, PrintStream out) {
        this.sc = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    public void closeReader() {
        this.sc.close();
    }

    public void prettyPrintToUser(String msg) {
        String indentedMsg = msg.replaceAll("(?m)^", "    ");
        out.printf("%s\n%s\n%s%n", BORDER, indentedMsg, BORDER);
    }

    public void welcomeMessage() {
        out.printf("Duke is gone. Hello, this is Duchess.\nHow can I help you?\n%s%n", BORDER);
    }

    public void goodbyeMessage() {
        prettyPrintToUser("It has been a pleasure, goodbye!");
    }

    public void taskAddedMessage(Task addedTask, int taskLeftNum) {
        String tasksLeft = REMAINING_TASK_NUM.replace(PLACEHOLDER, String.valueOf(taskLeftNum));
        String msg = String.format("%s\n  %s\n%s", ADDED_TASK, addedTask.toString(), tasksLeft);
        prettyPrintToUser(msg);
    }

    public void taskDeletedMessage(Task removedTask, int taskLeftNum) {
        String tasksLeft = REMAINING_TASK_NUM.replace(PLACEHOLDER, String.valueOf(taskLeftNum));
        String msg = String.format("%s\n  %s\n%s", DELETED_TASK, removedTask.toString(), tasksLeft);
        prettyPrintToUser(msg);
    }

    public void markedDoneMessage(Task task) {
        String msg = String.format("%s\n  %s", DONE_TASK, task.toString());
        prettyPrintToUser(msg);
    }

    public void printTaskListMessage(String stringifyTaskLast) {
        prettyPrintToUser(stringifyTaskLast);
    }

    public void printMatchingTasksMessage(List<Task> listOfMatches) {
        int len = listOfMatches.size();
        if (len == 0) {
            prettyPrintToUser(NO_MATCHING_TASKS);
        } else {
            StringBuilder msg = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < len; i++) {
                int currTaskNum = i + 1;
                msg.append("\n").append(currTaskNum).append(". ").append(listOfMatches.get(i).toString());
            }
            prettyPrintToUser(msg.toString());
        }
    }

    public void unknownCommandMessage() {
        prettyPrintToUser(UNKNOWN_COMMAND);
    }

    public static void showErrorMessage(String errorMsg) {
        System.err.println(errorMsg);
    }

    public void missingTaskMessage() {
        prettyPrintToUser(MISSING_TASK);
    }

    public void maxTaskReachedMessage() {
        prettyPrintToUser(MAX_TASK);
    }

}
