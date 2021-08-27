package duke;

import duke.task.Task;
import java.util.ArrayList;

public class Ui {
    private final String HORIZONTAL_LINE = "-------------------------------------------------------------";
    private final String ADD_FORMAT = "Got it. I've  added this task:\n  %s";
    private final String ERROR_FORMAT = "☹ OOPS!!! %s";
    private final String TASK_COUNT_FORMAT = "Now you have %d %s in the list.";
    private final String DONE_FORMAT = "Nice! I've marked this task as done:\n %s";
    private final String DELETE_FORMAT = "Noted. I've removed this task:\n  %s";

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printMessage("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showError(String message) {
        printMessage(String.format(ERROR_FORMAT, message));
    }

    public void showNewTask(Task task, int taskLength) {
        printMessageWithTaskCount(ADD_FORMAT, task, taskLength);;
    }

    public void showDeletedTask(Task task, int taskLength) {
        printMessageWithTaskCount(DELETE_FORMAT, task, taskLength);
    }

    public void showDoneTask(Task task) {
        printMessage(String.format(DONE_FORMAT, task));
    }

    public void showExit() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public void showTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            printMessage("Nothing in the list!");
        } else {
            printLine();
            for (int i = 0; i < tasks.size(); i++) {
                printWithTabIndent(String.format("%d. %s", i + 1, tasks.get(i).toString()));
            }
            printLine();
        }
    }

    public void showReadFile(String filePath) {
        printMessage(String.format("Reading tasks from %s...", filePath));
    }

    public void showFilteredTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            printMessage("No matching tasks found in the list.");
        } else {
            printLine();
            printWithTabIndent("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                printWithTabIndent(String.format("%d. %s", i + 1, tasks.get(i).toString()));
            }
            printLine();
        }
    }

    /**
     * Print with 4 spaces infront of param str.
     *
     * @param str A String to be printed
     */
    private void printWithTabIndent(String str) {
        System.out.println("\t" + str);
    }

    /**
     * Print horizontal line.
     */
    private void printLine() {
        printWithTabIndent(HORIZONTAL_LINE);
    }

    private void printMessage(String message) {
        printLine();
        printWithTabIndent(message.replace("\n", "\n\t"));
        printLine();
    }

    private void printMessageWithTaskCount(String format, Task task, int taskLength) {
        printMessage(String.format(format + "\n" + TASK_COUNT_FORMAT, task, taskLength,
                taskLength == 1 ? "task" : "tasks"));
    }
}
