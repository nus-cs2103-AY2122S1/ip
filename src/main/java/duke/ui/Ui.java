package duke.ui;

import java.util.Scanner;

import duke.exception.LoadingException;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner sc;

    /**
     * Sets up the ui scanner.
     */
    public Ui() {
        //User input.
        sc = new Scanner(System.in);
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        //Start Prompt.
        System.out.println(getPattern("Hello! I'm duke.Duke\n   What can I do for you?"));
    }

    /**
     * Returns the formatted string output.
     *
     * @param task The possible task string representations.
     * @param count The number of tasks.
     * @return The desirable output string related to task.
     */
    public String getOutputFrame(String task, int count) {
        String title = "   Got it. I've added this task:\n   ";
        String middle = "  " + task + "\n   ";
        String end = "Now you have " + count + " tasks in the list.";
        return title + middle + end;
    }

    /**
     * Returns formatted string output.
     *
     * @param r The input string.
     * @return Formatted string.
     */
    public String getPattern(String r) {
        StringBuilder result = new StringBuilder();
        StringBuilder curr = new StringBuilder();
        String empty = "   ";
        curr.append("*".repeat(80));
        String out = empty + curr + "\n" + empty + r + "\n" + empty + curr + "\n";
        result.append(out);
        return result.toString();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("   " + "*".repeat(80));
    }

    /**
     * Shows the error message.
     *
     * @param message
     */
    public void showError(String message) {
        System.out.println("   " + message);
    }

    /**
     * Shows all strings stored with indexing.
     *
     * @param tasks The list of tasks.
     * @param count The number of tasks.
     */
    public void showList(TaskList tasks, int count) {
        StringBuilder curr = new StringBuilder();
        String end = "\n   ";
        String begin = "   Here are the tasks in your list:\n   ";
        curr.append(begin);
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                end = "";
            }
            String out = ((i + 1)) + "." + tasks.elementToString(i) + end;
            curr.append(out);
        }
        System.out.println(curr);
    }

    /**
     * Shows the deleted task.
     *
     * @param shouldDelete The task should be deleted.
     * @param tasks The list of tasks.
     */
    public void showDelete(Task shouldDelete, TaskList tasks) {
        String title = "   Noted. I've removed this task: \n";
        String out = "     " + shouldDelete.toString() + "\n   ";
        String end = "Now you have " + tasks.getSize() + " tasks in the list.";
        System.out.println(title + out + end);
    }

    /**
     * Shows the tasks in the list.
     *
     * @param task The task want to be shown.
     * @param count The number of tasks in task list.
     */
    public void showTasks(String task, int count) {
        System.out.println(getOutputFrame(task, count));
    }

    /**
     * Shows error while loading.
     */
    public void showLoadingError() {
        LoadingException e = new LoadingException();
        System.out.println(e.getMessage());
    }

    /**
     * Shows the task marked as done.
     *
     * @param stringForm The string form of that task.
     */
    public void showDone(String stringForm) {
        String title = "   Nice! I've marked this task as done: \n";
        String out = "     " + stringForm;
        System.out.println(title + out);
    }

    public void showBye() {
        System.out.println("   " + "Bye, see you soon. ^-^");
    }
}
